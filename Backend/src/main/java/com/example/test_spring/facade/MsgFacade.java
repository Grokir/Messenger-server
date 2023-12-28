package com.example.test_spring.facade;

import com.example.test_spring.dto.request.MsgRequest;
import com.example.test_spring.dto.response.MsgResponse;
import com.example.test_spring.model.Msg;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MsgFacade {
    public MsgResponse toResponse(Msg msg) {
        return MsgResponse.builder()
                .id(msg.getId())
                .user_id(msg.getUserId())
                .msg_text(msg.getMsgText())
                .date_dispatch(msg.getTimeMessage())
                .build();
    }

    public MapSqlParameterSource toCreateMsg(MsgRequest msgRequest) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("id", msgRequest.getId());
        in.addValue("user_id", msgRequest.getUserId());
        in.addValue("message", msgRequest.getMsgText());
        in.addValue("time_message", msgRequest.getTimeMessage());
        return in;
    }

    public MapSqlParameterSource toUpdateMsg(MsgRequest msgRequest) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("id", msgRequest.getId());
        in.addValue("message", msgRequest.getMsgText());
        return in;
    }


    public Msg toMsg(MsgRequest msgRequest) {
        Msg msg = new Msg();
        msg.setMsgText(msgRequest.getMsgText());
        msg.setUserId(msgRequest.getUserId());
        msg.setTimeMessage(msgRequest.getTimeMessage());
        return msg;
    }

}