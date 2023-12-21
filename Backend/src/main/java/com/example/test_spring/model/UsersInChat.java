package com.example.test_spring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "users_in_chat", schema = "public", catalog = "chat")
public class UsersInChat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "chat_id", nullable = true)
    private UUID chatId;
    @Basic
    @Column(name = "user_id", nullable = true)
    private UUID userId;
    @Basic
    @Column(name = "was_deleted", nullable = false)
    private boolean wasDeleted;
}
