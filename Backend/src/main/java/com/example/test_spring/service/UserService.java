package com.example.test_spring.service;

import com.example.test_spring.dto.request.UserRequest;
import com.example.test_spring.dto.response.ChatResponse;
import com.example.test_spring.dto.response.UserResponse;
import com.example.test_spring.facade.ChatFacade;
import com.example.test_spring.facade.UserFacade;
import com.example.test_spring.model.User;
import com.example.test_spring.model.UsersInChat;
import com.example.test_spring.repository.ChatRepository;
import com.example.test_spring.repository.UserRepository;
import com.example.test_spring.repository.UsersInChatRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
//@Transactional
public class UserService implements BaseService<UserRequest, UserResponse> {

    private final UserFacade userFacade;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final UserRepository userRepository;
    private final UsersInChatRepository usersInChatRepository;
    private final ChatRepository chatRepository;
    private final ChatFacade chatFacade;

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public UserResponse findUserByFirstName(String column, String param) {
//        this.namedParameterJdbcTemplate.
////        return userFacade.toResponse(userRepository.findUserByName(username).orElseThrow(() -> new RuntimeException("Username not found with username " + username)));
//    }

//    public List<UserResponse> findUserByFirstName(String param) {
//        List<User> list = userRepository.findAllByParamLike(param);
//        List<UserResponse> result = null;
//        for(User u : list){
//            result.add(userFacade.toResponse(u));
//        }
//        int num = result.size();
//        return result;
//    }


        @Override
    public UserResponse getReferenceById(String id) {
        return null;
    }

    @Override
    public Boolean create(UserRequest userRequest) {
        if (
        this.namedParameterJdbcTemplate.update("""
                INSERT INTO chat.public."user" (name, surname, login, password)
                VALUES (:name, :surname, :login, :password);
                """, userFacade.toCreateUser(userRequest)) > 0
        ){
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(UserRequest userRequest, String id) {

        if(
                this.namedParameterJdbcTemplate.update("""
                                UPDATE chat.public."user" 
                                SET name = :name, 
                                    surname = :surname,
                                    login = :login, 
                                    password = :password 
                                WHERE id = :id::uuid""",
                        userFacade.toUpdateUser(userRequest, id)
                ) > 0
        )
        {
            return true;
        }

        return false;
    }

    @Override
    public Boolean delete(String id) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("id", id);
        if (this.namedParameterJdbcTemplate.update(
                """
                        UPDATE chat.public.user SET 
                        name = "DELETED", 
                        surname = "DELETED", 
                        login = "DELETED"
                        WHERE id=:id::uuid;""", in) > 0
        ) {
            return true;
        }
        return false;
    }

    public UserResponse findUserByName(String name) {
        return userFacade.toResponse(userRepository.findUserByName(name).orElseThrow(() -> new RuntimeException("Username not found with username " + name)));
    }

    public List<UserResponse> findAllUsersByName(String name) {
        List<User> userList = userRepository.findAllByNameLike(name);
        List<UserResponse> resList = new ArrayList<>();

        for(User u: userList) {
            if(u.getName().equals("DELETED")  &&  u.getSurname().equals("DELETED")) {
                continue;
            }
            resList.add(userFacade.toResponse(u));
        }

        return resList;
    }
    public UserResponse findUserByLogin(String login) {
        User tmpUser = userRepository.findUserByLogin(login).orElseThrow(() -> new RuntimeException("Username not found with login " + login));
        if (tmpUser.getName().equals("DELETED") || tmpUser.getSurname().equals("DELETED"))
            return userFacade.toResponse(new User());
        return userFacade.toResponse(tmpUser);
    }

    public String getPasswordById(UUID userId){
        User check = userRepository.findUserById(userId).orElseThrow(() -> new RuntimeException("Username not found with id " + userId));
        if (check.getName().equals("DELETED") || check.getSurname().equals("DELETED"))
            return null;

        return userRepository.getPasswordById(userId);
    }

    public List<UserResponse> showAllUsers() {
        List<User> userList = userRepository.showAll();
        List<UserResponse> resList = new ArrayList<>();

        for(User u: userList) {

            if(u.getName().equals("DELETED")  &&  u.getSurname().equals("DELETED")) {
                continue;
            }
//            u.setId(null);
            u.setPassword("");
            resList.add(userFacade.toResponse(u));

        }

        return resList;
    }

    public JSONArray showAllChats(String userId){
        List<UsersInChat> chatList = usersInChatRepository.findAllChatsByUserId(UUID.fromString(userId));
        List<ChatResponse> chats = new ArrayList<>();

        for(UsersInChat uic : chatList){
            chats.add(chatFacade.toResponse(chatRepository.findById(uic.getChatId()).orElseThrow(() -> new RuntimeException("Username not found with login " + userId))));
        }

        JSONArray res = new JSONArray();
        res.addAll(chats);
        return res;
    }

    public Boolean existUserWithLogin(String login){
        return userRepository.existUser(login);
    }

}
