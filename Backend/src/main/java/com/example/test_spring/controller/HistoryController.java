package com.example.test_spring.controller;


import com.example.test_spring.dto.request.ChatRequest;
import com.example.test_spring.dto.request.UsersInChatRequest;
import com.example.test_spring.service.ChatService;
import com.example.test_spring.service.UsersInChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class HistoryController {

    // обавить аннотацию для приватных полей

    private final ChatService chatService;
    private final UsersInChatService usersInChatService;


//    public HistoryController(ChatService chatService, UsersInChatService uichService) {
//        this.chatService = chatService;
//        this.usersInChatService = uichService;
//    }

////  Получаем список всех чатов
//    @GetMapping()
//    public ... getListAllChats(...);


////
    @PostMapping("/create")
    public ResponseEntity<String> createChat(@RequestBody ChatRequest chatRequest){
        return new ResponseEntity<>("Add record: " + chatService.create(chatRequest), HttpStatus.OK);
    }
    @PostMapping("/add_user")
    public ResponseEntity<String> addUser(@RequestBody UsersInChatRequest usersInChatRequest){
        return new ResponseEntity<>("Add record: " + usersInChatService.create(usersInChatRequest), HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@PathVariable String chat_id){
        return null;
    }

////    На id чата присылаются сообщения и добавляются в БД
//    @PostMapping("/{chat_id}")
//    public ResponseEntity<String> addMsg(@ResponseBody MsgRequest msg_req){
//
//    }



}
