package com.example.test_spring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private UUID id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
}