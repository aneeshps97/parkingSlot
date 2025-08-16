package com.example.parkingslot.repository;

import com.example.parkingslot.entity.ParkingAreaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingLotUserDAO extends JpaRepository<ParkingAreaUser, Integer> {
    List<ParkingAreaUser> findByUserId(int userId);
}
