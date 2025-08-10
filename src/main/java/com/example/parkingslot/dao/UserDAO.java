package com.example.parkingslot.dao;

import com.example.parkingslot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User,Integer> {
    Optional<Object> findByEmail(String email);
}
