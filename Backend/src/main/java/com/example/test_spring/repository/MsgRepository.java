package com.example.test_spring.repository;


import com.example.test_spring.model.Msg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MsgRepository extends JpaRepository<Msg, UUID> {
}
