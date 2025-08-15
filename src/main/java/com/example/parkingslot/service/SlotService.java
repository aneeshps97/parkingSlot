package com.example.parkingslot.service;

import com.example.parkingslot.entity.Slot;
import org.springframework.stereotype.Service;

@Service
public interface SlotService{
    public Slot createSlot(Slot slot) throws Exception ;
}
