package com.example.test_spring.service;

import com.example.test_spring.dto.request.UsersInChatRequest;
import com.example.test_spring.dto.response.UsersInChatResponse;
import com.example.test_spring.facade.UsersInChatFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersInChatService implements BaseService<UsersInChatRequest, UsersInChatResponse> {
    private final UsersInChatFacade uichFacade;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean create(UsersInChatRequest usersInChatRequest) {

        List<MapSqlParameterSource> list = uichFacade.createSQLRequest(usersInChatRequest);
        for(MapSqlParameterSource obj : list) {
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
    public Boolean update(UsersInChatRequest request, String id) {
        return null;
    }

    @Override
    public UsersInChatResponse getReferenceById(String id) {
        return null;
    }
}
