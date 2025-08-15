package com.example.parkingslot.dao;

import com.example.parkingslot.entity.ParkingAreaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingAreaUserDAO extends JpaRepository<ParkingAreaUser, Integer> {
    List<ParkingAreaUser> findByUserId(int userId);
}
