package com.example.test_spring.service;

import com.example.test_spring.dto.request.HistoryRequest;
import com.example.test_spring.dto.response.HistoryResponse;
import com.example.test_spring.facade.HistoryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class HistoryService implements BaseService<HistoryRequest, HistoryResponse>{

    private final HistoryFacade historyFacade;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean create(HistoryRequest request) {
        if (
                this.namedParameterJdbcTemplate.update("""
                INSERT INTO chat.public."history" (chat_id, msg_id)
                VALUES (:chat_id, :msg_id);
                """, historyFacade.createSQLRequest(request)) > 0
        ){
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(HistoryRequest request) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public HistoryResponse getReferenceById(String id) {
        return null;
    }
}
