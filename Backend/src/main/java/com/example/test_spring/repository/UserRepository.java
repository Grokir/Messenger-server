package com.example.test_spring.repository;

import com.example.test_spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserById(UUID userId);

    @Query("SELECT u FROM User u")
    List<User> showAll();

    @Query(value = """
        SELECT *
        FROM public."user" u
        WHERE u.login LIKE :searchStr
        OR u.name LIKE :searchStr
        OR u.surname LIKE :searchStr

    """, nativeQuery = true)
    List<User> findAllBySearchStr(@Param("searchStr") String searchStr);

    @Query(value = """
        SELECT public."user".password
        FROM public."user"
        WHERE id = :userId
    """,nativeQuery = true)
    String getPasswordById(@Param("userId") UUID userId);

    @Query(value = """
            SELECT EXISTS(
                    SELECT u.*
                    FROM public.user u
                    WHERE u.login = :login
                    )
    """, nativeQuery = true)
    Boolean existUser(@Param("login") String login);

}
