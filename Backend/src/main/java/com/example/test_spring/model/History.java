package com.example.test_spring.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "history", schema = "public", catalog = "chat")
public class History {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "chat_id", nullable = false)
    private Object chatId;
    @Basic
    @Column(name = "msg_id", nullable = false)
    private Object msgId;
}
