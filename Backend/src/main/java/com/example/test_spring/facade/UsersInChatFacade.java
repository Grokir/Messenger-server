package com.example.test_spring.facade;

import com.example.test_spring.dto.request.UsersInChatRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.simple.JSONArray;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class UsersInChatFacade {
    @SneakyThrows
    public List<MapSqlParameterSource> createSQLRequest(UsersInChatRequest inChatRequest) {
        List<MapSqlParameterSource> res = new ArrayList<>();
        JSONArray list_id = inChatRequest.getArrUserId();

        for(Integer i = 0; i < list_id.size(); i++){
            MapSqlParameterSource in = new MapSqlParameterSource();
            String tmp = list_id.get(i).toString();
            in.addValue("user_id", UUID.fromString(tmp));
            in.addValue("chat_id", inChatRequest.getId());
            res.add(in);
        }
        return res;
    }


}