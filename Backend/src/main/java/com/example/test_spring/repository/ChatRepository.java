package com.example.test_spring.repository;


import com.example.test_spring.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface ChatRepository extends JpaRepository<Chat, UUID> {
    @Query(value = """
            SELECT c.*
            FROM public.chat c
            WHERE c.id = :chatId
            LIMIT 501
            """
            , nativeQuery = true)
    Optional<Chat> findById(@Param("chatId") UUID chat);
}
