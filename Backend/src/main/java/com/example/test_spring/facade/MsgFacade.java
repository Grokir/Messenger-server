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
                .id(msg.getId_msg())
                .user_id(msg.getUser_id())
                .msg_text(msg.getMsg_text())
                .date_dispatch(msg.getDate_dispatch())
                .build();
    }

    public MapSqlParameterSource toCreateMsg(MsgRequest msgRequest) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("user_id", msgRequest.getUser_id());
        in.addValue("message", msgRequest.getMsg_text());
        in.addValue("time_message", msgRequest.getDate_dispatch());
        return in;
    }

    public MapSqlParameterSource toUpdateMsg(MsgRequest msgRequest, String msgId) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("id", msgId);
        in.addValue("message", msgRequest.getMsg_text());
        return in;
    }


    public Msg toMsg(MsgRequest msgRequest) {
        Msg msg = new Msg();
        msg.setMsg_text(msgRequest.getMsg_text());
        msg.setUser_id(msgRequest.getUser_id());
        msg.setDate_dispatch(msgRequest.getDate_dispatch());
        return msg;
    }

}