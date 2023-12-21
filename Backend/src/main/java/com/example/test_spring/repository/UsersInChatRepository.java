package com.example.test_spring.repository;


import com.example.test_spring.model.UsersInChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface UsersInChatRepository extends JpaRepository<UsersInChat, Long> {
    @Query(value = """
            SELECT uin.*
            FROM public.users_in_chat uin
            WHERE uin.user_id = :userId
            LIMIT 501
            """
            , nativeQuery = true)
    List<UsersInChat> findAllChatsByUserId(@Param("userId") UUID userId);

    @Query(value = """
            SELECT EXISTS(
                    SELECT uin.*
                    FROM public.users_in_chat uin
                    WHERE uin.chat_id = :chatId AND uin.user_id = :userId 
                    )
""", nativeQuery = true)
    Boolean existUserInChat(@Param("userId") UUID userId, @Param("chatId") UUID chatId);
}
