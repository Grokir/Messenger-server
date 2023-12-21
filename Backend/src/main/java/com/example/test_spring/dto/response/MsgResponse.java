package com.example.test_spring.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class MsgResponse implements Serializable {
    private UUID id;
    private UUID user_id;
    private String msg_text;
    private LocalDateTime date_dispatch;
}