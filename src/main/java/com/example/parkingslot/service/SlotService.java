package com.example.parkingslot.service;

import com.example.parkingslot.entity.Slot;
import com.example.parkingslot.exceptionhandler.ParkingSlotException;
import org.springframework.stereotype.Service;

@Service
public interface SlotService{
    public Slot createSlot(Slot slot,int ParkingAreaId) throws ParkingSlotException ;
    public void deleteSlot(int slotId) throws ParkingSlotException;
    public Slot updateSlot(int slotId, Slot slot) throws ParkingSlotException;
}
