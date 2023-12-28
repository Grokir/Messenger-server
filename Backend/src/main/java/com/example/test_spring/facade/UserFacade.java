package com.example.test_spring.facade;

import com.example.test_spring.dto.request.UserRequest;
import com.example.test_spring.dto.response.UserResponse;
import com.example.test_spring.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {
    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }

    public MapSqlParameterSource toUserSQL(User user) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("name", user.getName());
        in.addValue("surname", user.getSurname());
        in.addValue("login", user.getLogin());
        in.addValue("password", new User().hash_password(user.getPassword()));
        return in;
    }


    public MapSqlParameterSource toCreateUser(UserRequest userRequest) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("name", userRequest.getName());
        in.addValue("surname", userRequest.getSurname());
        in.addValue("login", userRequest.getLogin());
        in.addValue("password", new User().hash_password(userRequest.getPassword()));
        return in;
    }

    public MapSqlParameterSource toUpdateUser(UserRequest userRequest) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("id", userRequest.getId());
        in.addValue("login", userRequest.getLogin());
        in.addValue("password", new User().hash_password(userRequest.getPassword()));
        in.addValue("name", userRequest.getName());
        in.addValue("surname", userRequest.getSurname());
        return in;
    }


    public MapSqlParameterSource toDeleteUser(UserRequest userRequest) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("id", userRequest.getId());
        userRequest.getId();
        return in;
    }

    public User toUser(UserRequest userRequest) {
        User user = new User();
        user.setLogin(userRequest.getLogin());
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        return user;
    }

}