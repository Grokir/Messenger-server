package com.example.test_spring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "history", schema = "public", catalog = "chat")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "chat_id", nullable = false)
    private UUID chatId;
    @Basic
    @Column(name = "msg_id", nullable = false)
    private UUID msgId;
}
