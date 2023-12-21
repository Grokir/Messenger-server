package com.example.test_spring.model;


import jakarta.persistence.*;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

@Data
@Entity
@Table(name = "user", schema = "public", catalog = "chat")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Basic
    @Column(name = "login")
    private String login;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "surname")
    private String surname;

    public String hash_password(final String pass) {
            String pass_salt_prefix = "VlFhK3:-SdZ=";
            String pass_salt_postfix = "sbD:MDkf#YsT";
            String salted_password = pass_salt_prefix + pass + pass_salt_postfix;
            return DigestUtils.sha256Hex(salted_password);
    }
}
