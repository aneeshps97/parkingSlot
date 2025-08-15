package com.example.parkingslot.dao;

import com.example.parkingslot.entity.Booking;
import com.example.parkingslot.entity.ParkingArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingAreaDao extends JpaRepository<ParkingArea,Integer> {
}
