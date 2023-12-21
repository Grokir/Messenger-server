package com.example.test_spring.facade;


import com.example.test_spring.dto.request.HistoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class HistoryFacade {

    public MapSqlParameterSource createSQLRequest(HistoryRequest historyRequest) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("chat_id", historyRequest.getChatId());
        in.addValue("msg_id", historyRequest.getMsgId());
        return in;
    }

}
