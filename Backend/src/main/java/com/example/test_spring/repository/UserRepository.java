package com.example.test_spring.repository;

import com.example.test_spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
//    Optional<User> findUserByName(String username);
//    ;
//    Optional<User> findUserByLogin(String login);
//    Optional<User> findUserById(Long userid);

//    @Query("SELECT * FROM User WHERE name LIKE ?1")
//    List<User> findAllByParamLike(String param);

}
