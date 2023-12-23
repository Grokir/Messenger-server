package com.example.test_spring.controller;


import com.example.test_spring.dto.request.MsgRequest;
import com.example.test_spring.facade.MsgFacade;
import com.example.test_spring.model.Msg;
import com.example.test_spring.service.MsgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/chat/")
@RequiredArgsConstructor
public class MsgController {

    final private MsgService msgService;
    final private MsgFacade msgFacade;


    @PostMapping("/{chatId}/msg/add")
    public ResponseEntity<String> addMsg(@RequestBody MsgRequest msgRequest, @PathVariable String chatId) {
        LocalDateTime time_request = LocalDateTime.now();


        msgRequest.setTimeMessage(time_request);
        Boolean isCreated =  msgService.create(msgRequest);
        Msg msg = msgFacade.toMsg(msgRequest);
        return new ResponseEntity<>("Add msg: " + isCreated, HttpStatus.OK);
    }
}
