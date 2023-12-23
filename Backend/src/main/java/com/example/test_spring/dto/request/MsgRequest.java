package com.example.test_spring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgRequest {
    private UUID id;
    private UUID userId;
    private String msgText;
    private LocalDateTime timeMessage;
}