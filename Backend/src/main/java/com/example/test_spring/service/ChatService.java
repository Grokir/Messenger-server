package com.example.test_spring.service;

import com.example.test_spring.dto.request.ChatRequest;
import com.example.test_spring.dto.response.ChatResponse;
import com.example.test_spring.facade.ChatFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService implements BaseService<ChatRequest, ChatResponse>{
    private final ChatFacade chatFacade;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ChatResponse getReferenceById(String id) {
        return null;
    }

    @Override
    public Boolean create(ChatRequest chatRequest) {
        Integer isChatCreated = this.namedParameterJdbcTemplate.update("""
                INSERT INTO chat.public."chat" (title)
                VALUES (:title);
                """, chatFacade.toCreateChat(chatRequest));

        if (isChatCreated > 0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(ChatRequest chatRequest) {
        Integer isChatWasUpdated = this.namedParameterJdbcTemplate.update("""
                                UPDATE chat.public."chat" 
                                SET title = :title
                                WHERE id = :id::uuid""",
                chatFacade.toUpdateChat(chatRequest));

        if(isChatWasUpdated > 0)
        {
            return true;
        }

        return false;
    }

    @Override
    public Boolean delete(String id) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("id", id);
        Integer isChatWasDelete = this.namedParameterJdbcTemplate.update("""
                        UPDATE chat.public."chat" SET 
                        is_delete = true
                        WHERE id=:id::uuid;""", in);

        if (isChatWasDelete > 0) {
            return true;
        }
        return false;
    }
}
