package com.springboot.repositories;

import com.springboot.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from users u where u.username = :username")
    User findByUsername(@Param("username") String userName);
}
