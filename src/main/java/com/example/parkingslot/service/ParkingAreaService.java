package com.example.parkingslot.service;

import com.example.parkingslot.entity.ParkingArea;
//import com.example.parkingslot.entity.ParkingAreaSlots;
//import com.example.parkingslot.entity.ParkingAreaUser;
import com.example.parkingslot.entity.Slot;
import com.example.parkingslot.entity.User;
import com.example.parkingslot.exceptionhandler.ParkingSlotException;

import java.util.List;

public interface ParkingAreaService {
    public ParkingArea createParkingArea(ParkingArea parkingArea) throws ParkingSlotException;
    public ParkingArea addSlotsToParkingArea(int parkingAreaId,List<Slot> slots) throws ParkingSlotException;
    public ParkingArea addUsersToParkingArea(int ParkingAreaId, List<Integer> userIds) throws ParkingSlotException;
    public ParkingArea findParkingAreaById(int parkingAreaId) throws ParkingSlotException;

   //public ParkingAreaSlots addSlotsToParkingArea(ParkingAreaSlots parkingAreaSlots)throws Exception;
    //public ParkingAreaUser addUsersToParkingArea(ParkingAreaUser parkingAreaUser) throws Exception;
   // public List<ParkingArea> findParkingAreaByUser(int user_id)throws Exception;
}
