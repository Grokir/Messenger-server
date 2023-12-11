package com.example.test_spring.service;

import com.example.test_spring.dto.request.MsgRequest;
import com.example.test_spring.dto.response.MsgResponse;
import com.example.test_spring.facade.MsgFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MsgService implements BaseService<MsgRequest, MsgResponse>{
    private final MsgFacade msgFacade;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public MsgResponse getReferenceById(String id) {
        return null;
    }

    @Override
    public Boolean create(MsgRequest msgRequest) {
        if (
                this.namedParameterJdbcTemplate.update("""
                INSERT INTO chat.public."msg" (user_id, message, time_message)
                VALUES (:user_id, :message, :time_message);
                """, msgFacade.toCreateMsg(msgRequest)) > 0
        ){
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(MsgRequest msgRequest, String id) {

        if(
                this.namedParameterJdbcTemplate.update("""
                                UPDATE chat.public."msg" 
                                SET user_id = :user_id, 
                                    message = :message,
                                    time_message = :time_message
                                WHERE id = :id::uuid""",
                        msgFacade.toUpdateMsg(msgRequest, id)
                ) > 0
        )
        {
            return true;
        }

        return false;
    }

    @Override
    public Boolean delete(String id) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("id", id);
        if (this.namedParameterJdbcTemplate.update(
                """

                        UPDATE chat.public."msg" SET 
                        message = NULL
                        WHERE id=:id::uuid;""", in) > 0
        ) {
            return true;
        }
        return false;
    }
}
