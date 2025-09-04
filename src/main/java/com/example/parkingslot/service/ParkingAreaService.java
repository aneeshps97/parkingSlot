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
    public ParkingArea updateNameOfParkingArea(int parkingAreaId,String newName) throws ParkingSlotException;
    public ParkingArea removeUserFromParkingArea(int parkingAreaId, int userId) throws ParkingSlotException;
    public ParkingArea deleteParkingArea(int parkingAreaId) throws ParkingSlotException;
}
