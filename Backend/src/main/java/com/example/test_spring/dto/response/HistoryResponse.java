package com.example.test_spring.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@Builder
public class HistoryResponse {
    private UUID chatId;
    private UUID msgId;
}
