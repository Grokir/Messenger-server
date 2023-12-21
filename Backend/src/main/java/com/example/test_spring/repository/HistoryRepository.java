package com.example.test_spring.repository;


import com.example.test_spring.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query(value = """
            SELECT t.id, t.chat_id, t.msg_id 
            FROM public.history t 
            WHERE chat_id = :chatId 
            LIMIT 501
            """
            , nativeQuery = true)
    List<History> findAllByChatId(@Param("chatId") UUID chatId);

}
