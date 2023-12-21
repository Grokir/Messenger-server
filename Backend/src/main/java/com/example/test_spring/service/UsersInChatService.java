package com.example.test_spring.service;

import com.example.test_spring.dto.request.UsersInChatRequest;
import com.example.test_spring.dto.response.UsersInChatResponse;
import com.example.test_spring.facade.UsersInChatFacade;
import com.example.test_spring.repository.UsersInChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersInChatService implements BaseService<UsersInChatRequest, UsersInChatResponse> {
    private final UsersInChatFacade uichFacade;
    private final UsersInChatRepository usersInChatRepository;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Boolean addUsers(UsersInChatRequest usersInChatRequest, String chatId){

        List<MapSqlParameterSource> list = uichFacade.createSQLRequest(usersInChatRequest, chatId);

        for(MapSqlParameterSource obj : list) {

            if(usersInChatRepository.existUserInChat((UUID)obj.getValue("user_id"), UUID.fromString(chatId))){
                continue;
            }

            if
            (
                    this.namedParameterJdbcTemplate.update("""
                    INSERT INTO chat.public."users_in_chat" (chat_id, user_id)
                    VALUES (:chat_id, :user_id);
                    """, obj) <= 0
            )
            {
                return false;
            }
        }
        return true;
    }


    @Override
    public Boolean delete(String id){
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("id", id);

        if (this.namedParameterJdbcTemplate.update(
                """
                        UPDATE chat.public.users_in_chat SET  
                        was_deleted = true
                        WHERE id=:id::uuid;""", in) > 0
        ) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(UsersInChatRequest request, String chatId) {
        List<MapSqlParameterSource> list = uichFacade.createSQLRequest(request, chatId);
        for(MapSqlParameterSource obj : list) {
            if
            (
                    this.namedParameterJdbcTemplate.update("""
                        
                            UPDATE chat.public.users_in_chat SET  
                        was_deleted = true
                        WHERE user_id=:user_id::uuid and chat_id =:chatId::uuid;
                        """, obj) <= 0
            )
            {
            return false;
            }
        }
        return true;
    }

    @Override
    public UsersInChatResponse getReferenceById(String id) {
        return null;
    }

    @Override
    public Boolean create(UsersInChatRequest usersInChatRequest) {
        return null;
    }
}
