package com.example.test_spring.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersInChatRequest {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("arrUserId")
    private JSONArray arrUserId;
    @JsonProperty("wasDeleted")
    private Boolean wasDeleted;
}
