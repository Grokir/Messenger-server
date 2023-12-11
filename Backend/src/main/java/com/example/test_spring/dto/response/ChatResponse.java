package com.example.test_spring.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ChatResponse {
    private UUID id;
    private String title;
}
