package com.example.test_spring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "msg", schema = "public", catalog = "chat")
public class Msg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Basic
    @Column(name = "user_id")
    private UUID userId;

    @Basic
    @Column(name = "message")
    private String msgText;

    @Basic
    @Column(name = "time_message")
    private LocalDateTime timeMessage;

    @Basic
    @Column(name = "was_delete")
    private Boolean wasDelete;

//    private SimpleDateFormat format_date= new SimpleDateFormat("dd.MM.yyyy");
//    private SimpleDateFormat format_time = new SimpleDateFormat("HH:mm:ss");
//
//    public String getStringDate(){
//        return format_date.format(timeMessage);
//    }
//
//    public String getStringTime(){
//        return format_time.format(timeMessage);
//    }
//
//    public LocalDateTime getRAWDate(){
//        return timeMessage;
//    }
}
