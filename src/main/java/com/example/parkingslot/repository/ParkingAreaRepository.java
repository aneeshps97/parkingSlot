package com.example.parkingslot.repository;

import com.example.parkingslot.entity.ParkingArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingAreaRepository extends JpaRepository<ParkingArea,Integer> {
}
