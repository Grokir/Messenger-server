package com.example.test_spring.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
public class UserResponse implements Serializable {
    private UUID id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
}