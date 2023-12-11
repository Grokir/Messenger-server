package com.example.test_spring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "chat", schema = "public", catalog = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id_chat;

    @Basic
    @Column(name = "title")
    private String title_chat;
}
