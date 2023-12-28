package com.example.test_spring.service;

import com.example.test_spring.dto.request.HistoryRequest;
import com.example.test_spring.dto.request.MsgRequest;
import com.example.test_spring.dto.response.MsgResponse;
import com.example.test_spring.facade.HistoryFacade;
import com.example.test_spring.facade.MsgFacade;
import com.example.test_spring.model.History;
import com.example.test_spring.model.Msg;
import com.example.test_spring.repository.HistoryRepository;
import com.example.test_spring.repository.MsgRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MsgService implements BaseService<MsgRequest, MsgResponse>{
    private final MsgFacade msgFacade;
    private final MsgRepository msgRepository;
    private final HistoryFacade historyFacade;
    private final HistoryRepository historyRepository;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;



    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public MsgResponse getReferenceById(String id) {
        return null;
    }

    public UUID getMsgId(Msg msg){
//        MapSqlParameterSource in = new MapSqlParameterSource();
//        in.addValue("date_time", msg.getDate_dispatch());
//        in.addValue("user_id", msg.getUser_id());
//
//        namedParameterJdbcTemplate.query("""
//                SELECT id FROM chat.public.msg WHERE time_message=:date_time and user_id=:user_id;
//                """, in);
        return null;
    }

    @Override
    public Boolean create(MsgRequest msgRequest){
            return null;
    }

    public Boolean create(MsgRequest msgRequest, String chatId) {

        UUID idMsg = UUID.randomUUID();
        msgRequest.setId(idMsg);

        Integer addMsg = this.namedParameterJdbcTemplate.update("""
                INSERT INTO chat.public."msg" (id, user_id, message, time_message)
                VALUES (:id, :user_id, :message, :time_message);
                """, msgFacade.toCreateMsg(msgRequest));

        Integer addHistory = this.namedParameterJdbcTemplate.update("""
                INSERT INTO chat.public."history" (chat_id, msg_id)
                VALUES (:chat_id, :msg_id);
                """, historyFacade.createSQLRequest(new HistoryRequest(UUID.fromString(chatId), idMsg)));

        if (
                addMsg > 0
                &&
                addHistory > 0
        ){
            return true;
        }
        return false;

    }

    @Override
    public Boolean update(MsgRequest msgRequest) {

        if(
                this.namedParameterJdbcTemplate.update("""
                                UPDATE chat.public."msg" 
                                SET user_id = :user_id, 
                                    message = :message,
                                    time_message = :time_message
                                WHERE id = :id::uuid""",
                        msgFacade.toUpdateMsg(msgRequest)
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

                        UPDATE chat.public."msg" 
                        SET was_delete = true
                        WHERE id=:id::uuid;""", in) > 0
        ) {
            return true;
        }
        return false;
    }

    public MsgResponse findMsgByDate(LocalDateTime date) {
        return msgFacade.toResponse(msgRepository.findMsgByTimeMessage(Date.valueOf(date.toString())).orElseThrow(() -> new RuntimeException("Msg not found with date " + date)));
    }

    public JSONArray showAllMessgaes(String chatId){

        List<History> histories = historyRepository.findAllByChatId(UUID.fromString(chatId));

        List<MsgResponse> resList = new ArrayList<>();

        for(History h : histories) {
            UUID tmp = h.getMsgId();
            Msg msg = msgRepository.findMsgById(tmp).orElseThrow(() -> new RuntimeException("msg with id " + tmp + "not found!"));

            if (msg.getWasDelete())
                continue;

            msg.setMsgText(CryptoService.sendPOST("msgText", msg.getMsgText(), false));
            resList.add(msgFacade.toResponse(msg));
        }
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(resList);
        return jsonArray;
    }

}
