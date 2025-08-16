package com.example.parkingslot.repository;

import com.example.parkingslot.entity.ParkingAreaSlots;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotSlotsRepository extends JpaRepository<ParkingAreaSlots,Integer> {
}
