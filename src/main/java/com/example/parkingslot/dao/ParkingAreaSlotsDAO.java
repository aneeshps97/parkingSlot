package com.example.parkingslot.dao;

import com.example.parkingslot.entity.ParkingAreaSlots;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingAreaSlotsDAO extends JpaRepository<ParkingAreaSlots,Integer> {
}
