package com.example.test_spring.controller;

import com.example.test_spring.dto.request.UserRequest;
import com.example.test_spring.dto.response.UserResponse;
import com.example.test_spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")

    public ResponseEntity<String> create(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>("Add record: " + userService.create(userRequest), HttpStatus.OK);
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity<String> update(@RequestBody UserRequest userRequest, @PathVariable String userId){
        return new ResponseEntity<>("Update record: " + userService.update(userRequest, userId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> delete(@PathVariable String userId) {
        return new ResponseEntity<>("Delete record: " + userService.delete(userId), HttpStatus.OK);
    }

    @GetMapping("/{find_param}")
    public ResponseEntity<UserResponse> findUserByFirstName(
            @PathVariable String find_param
    ) {
        userService.findUserByFirstName(find_param);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
