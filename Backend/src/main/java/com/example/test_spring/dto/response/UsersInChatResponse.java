package com.example.test_spring.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.json.simple.JSONArray;

import java.util.UUID;

@Data
@Builder
public class UsersInChatResponse {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("chatId")
    private UUID chatId;
    @JsonProperty("arrUserId")
    private JSONArray arrUserId; // должен быть массивом
    @JsonProperty("wasDeleted")
    private Boolean wasDeleted;
}
