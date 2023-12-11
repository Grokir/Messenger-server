package com.example.test_spring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "msg", schema = "public", catalog = "chat")
public class Msg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id_msg;

    @Basic
    @Column(name = "user_id")
    private UUID user_id;

    @Basic
    @Column(name = "message")
    private String msg_text;

    @Basic
    @Column(name = "time_message")
    private Date date_dispatch;

    private SimpleDateFormat format_date= new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat format_time = new SimpleDateFormat("HH:mm:ss");

    public String getStringDate(){
        return format_date.format(date_dispatch);
    }

    public String getStringTime(){
        return format_time.format(date_dispatch);
    }

    public Date getRAWDate(){
        return date_dispatch;
    }
}
