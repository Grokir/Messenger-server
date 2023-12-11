package com.example.test_spring.service;

import com.example.test_spring.dto.request.UserRequest;
import com.example.test_spring.dto.response.UserResponse;
import com.example.test_spring.facade.UserFacade;
import com.example.test_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//@Transactional
public class UserService implements BaseService<UserRequest, UserResponse> {

    private final UserFacade userFacade;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final UserRepository userRepository;

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
                INSERT INTO chat.public."user" (name, surname, patronymic, login, password)
                VALUES (:name, :surname, :patronymic, :login, :password);
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
                                    patronymic = :patronymic, 
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
                        name = NULL, 
                        surname = NULL, 
                        patronymic = NULL, 
                        login = NULL, 
                        password = NULL 
                        WHERE id=:id::uuid;""", in) > 0
        ) {
            return true;
        }
        return false;
    }
}
