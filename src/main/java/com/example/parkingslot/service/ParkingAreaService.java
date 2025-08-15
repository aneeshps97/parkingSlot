package com.example.parkingslot.service;

import com.example.parkingslot.entity.ParkingArea;
import com.example.parkingslot.entity.ParkingAreaSlots;
import com.example.parkingslot.entity.ParkingAreaUser;

import java.util.List;

public interface ParkingAreaService {
    public ParkingArea createParkingArea(ParkingArea parkingArea) throws Exception;
    public ParkingAreaSlots addSlotsToParkingArea(ParkingAreaSlots parkingAreaSlots)throws Exception;
    public ParkingAreaUser addUsersToParkingArea(ParkingAreaUser parkingAreaUser) throws Exception;
    public List<ParkingArea> findParkingAreaByUser(int user_id)throws Exception;
}
