package com.example.test_spring.facade;

import com.example.test_spring.dto.request.ChatRequest;
import com.example.test_spring.dto.response.ChatResponse;
import com.example.test_spring.model.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatFacade {
    public ChatResponse toResponse(Chat chat) {
        return ChatResponse.builder()
                .id(chat.getId_chat())
                .title(chat.getTitle_chat())
                .build();
    }

    public MapSqlParameterSource toCreateChat(ChatRequest chatRequest) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("id", chatRequest.getId());
        in.addValue("title", chatRequest.getTitle());
        return in;
    }

    public MapSqlParameterSource toUpdateChat(ChatRequest chatRequest) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("id", chatRequest.getId());
        in.addValue("title", chatRequest.getTitle());
        return in;
    }


    public Chat toChat(ChatRequest chatRequest) {
        Chat chat = new Chat();
        chat.setTitle_chat(chatRequest.getTitle());
        return chat;
    }

}