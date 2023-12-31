package com.example.test_spring.controller;


import com.example.test_spring.dto.request.ChatRequest;
import com.example.test_spring.dto.request.MsgDelRequest;
import com.example.test_spring.dto.request.MsgRequest;
import com.example.test_spring.dto.request.UsersInChatRequest;
import com.example.test_spring.service.ChatService;
import com.example.test_spring.service.CryptoService;
import com.example.test_spring.service.MsgService;
import com.example.test_spring.service.UsersInChatService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class HistoryController {

    private final ChatService chatService;
    private final UsersInChatService usersInChatService;
    private final MsgService msgService;


    @PostMapping("/create")
    public ResponseEntity<String> createChat(@RequestBody ChatRequest chatRequest){
        return new ResponseEntity<>("Add record: " + chatService.create(chatRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{chatId}/delete")
    public ResponseEntity<String> deleteChat(@PathVariable String chatId){
        return new ResponseEntity<>("Add record: " + chatService.delete(chatId), HttpStatus.OK);
    }

    @PostMapping("/{chatId}/add_user")
    public ResponseEntity<String> addUser(@RequestBody UsersInChatRequest usersInChatRequest, @PathVariable String chatId){
        usersInChatRequest.setId(UUID.fromString(chatId));
        return new ResponseEntity<>("Add record: " + usersInChatService.addUsers(usersInChatRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{chatId}/delete_user")
    public  ResponseEntity<String> deleteUser(@RequestBody UsersInChatRequest usersInChatRequest, @PathVariable String chatId){
        usersInChatRequest.setId(UUID.fromString(chatId));
        return new ResponseEntity<>("Add record: " + usersInChatService.update(usersInChatRequest), HttpStatus.OK);
    }

    @PostMapping("/{chatId}/add_msg")
    public ResponseEntity<String> addMsg(@RequestBody MsgRequest msgRequest, @PathVariable String chatId){
        LocalDateTime time_requset = LocalDateTime.now();

        String decodeMsgText = CryptoService.sendPOST("msgText", msgRequest.getMsgText(), true);

        msgRequest.setMsgText(decodeMsgText);
        msgRequest.setTimeMessage(time_requset);
        Boolean isCreated = msgService.create(msgRequest, chatId);
        return new ResponseEntity<>("Add msg: " + isCreated, HttpStatus.OK);
    }

    @DeleteMapping("/{chatId}/delete_msg")
    public ResponseEntity<String> deleteSeveralMsg(@RequestBody MsgDelRequest delRequest){
        JSONArray msgArray = delRequest.getMsgIdArray();
        Boolean res = false;
        for(Integer i = 0; i < msgArray.size(); i++){
            res = msgService.delete((String) msgArray.get(i));
        }
        return new ResponseEntity<>("Delete msg: " + res, HttpStatus.OK);
    }


    @GetMapping("/{chatId}/show_all")
    public ResponseEntity<JSONArray> showAllMessages(@PathVariable String chatId){
        return new ResponseEntity<>(msgService.showAllMessgaes(chatId), HttpStatus.OK);
    }


}
