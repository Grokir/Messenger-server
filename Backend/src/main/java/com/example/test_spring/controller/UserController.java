package com.example.test_spring.controller;

import com.example.test_spring.dto.request.UserRequest;
import com.example.test_spring.dto.response.UserResponse;
import com.example.test_spring.model.User;
import com.example.test_spring.repository.UserRepository;
import com.example.test_spring.service.CryptoService;
import com.example.test_spring.service.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<JSONObject> checkLogin(@RequestBody UserRequest userRequest){
//         UserRequest прадставляет собой следующий JSON
//         {
//           "login": "....",
//           "password": "...."
//         }
//


        JSONObject json = new JSONObject();

        if(!userService.existUserWithLogin(userRequest.getLogin())){
            json.put("status", "user not exist or wrong login");
            return new ResponseEntity<>(json, HttpStatus.NOT_ACCEPTABLE);
        }
        User user = userRepository.findUserByLogin(userRequest.getLogin()).orElseThrow();
        String checkPass = userService.getPasswordById(user.getId());
        String decodePassword = CryptoService.sendPOST("password", userRequest.getPassword(), true);
        if(checkPass.equals(user.hash_password(decodePassword))){
            json.put("status", "OK");
            return new ResponseEntity<>(json, HttpStatus.OK);
        }
        json.put("status", "wrong password");
        return new ResponseEntity<>(json, HttpStatus.NOT_ACCEPTABLE);

    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UserRequest userRequest){

        String decodePassword = CryptoService.sendPOST("password", userRequest.getPassword(), true);
        userRequest.setPassword(decodePassword);
        return new ResponseEntity<>("Add record: " + userService.create(userRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity<String> update(@RequestBody UserRequest userRequest, @PathVariable String userId){
        return new ResponseEntity<>("Update record: " + userService.update(userRequest, userId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> delete(@PathVariable String userId) {
        return new ResponseEntity<>("Delete record: " + userService.delete(userId), HttpStatus.OK);
    }

    @GetMapping("/get_id/{loginCurrentuser}")
    public ResponseEntity<JSONObject> getUserId(@PathVariable String loginCurrentuser){
        JSONObject json = new JSONObject();
        UserResponse tmp = userService.findUserByLogin(loginCurrentuser);
        json.put("id", tmp.getId());
        json.put("login", tmp.getLogin());
//        json.put("password", userService.getPasswordById(tmp.getId()));

        return new ResponseEntity<>(json, HttpStatus.OK);
    }
    

    @GetMapping("/find/{searchString}")
    public ResponseEntity<JSONArray> findUsers(@PathVariable String searchString){
        List<UserResponse> user_list =  userService.findAllUsersByStr(searchString);

        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(user_list);

        return new ResponseEntity<>(jsonArray, HttpStatus.OK);
    }

    @GetMapping("/find_by_login/{userLogin}")
    public ResponseEntity<UserResponse> findUserByLogin(@PathVariable String userLogin) {
        return new ResponseEntity<>(userService.findUserByLogin(userLogin), HttpStatus.OK);
    }

    @GetMapping("/show_all")
    public ResponseEntity<JSONArray> showAllUsers(){
        List<UserResponse> users = userService.showAllUsers();

        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(users);

        return new ResponseEntity<>(jsonArray, HttpStatus.OK);
    }

    //  Получаем список всех чатов
    @GetMapping("/{userId}/chats")
    public ResponseEntity<JSONArray> getListAllChats(@PathVariable String userId){
        return new ResponseEntity<>(userService.showAllChats(userId), HttpStatus.OK);
    }
}
