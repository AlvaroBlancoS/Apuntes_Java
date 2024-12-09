package com.practice.springData2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.practice.springData2.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username); // Cambiado a "existsByUsername"
    @Modifying
    @Query("DELETE FROM User u WHERE u.username = :username")
    void deleteByUsername(@Param("username") String username);

}
