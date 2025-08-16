package com.example.parkingslot.service;

import com.example.parkingslot.entity.ParkingLot;
import com.example.parkingslot.entity.ParkingAreaSlots;
import com.example.parkingslot.entity.ParkingAreaUser;

import java.util.List;

public interface ParkingAreaService {
    public ParkingLot createParkingArea(ParkingLot parkingLot) throws Exception;
    public ParkingAreaSlots addSlotsToParkingArea(ParkingAreaSlots parkingAreaSlots)throws Exception;
    public ParkingAreaUser addUsersToParkingArea(ParkingAreaUser parkingAreaUser) throws Exception;
    public List<ParkingLot> findParkingAreaByUser(int user_id)throws Exception;
}
