package com.example.parkingslot.service;

import com.example.parkingslot.repository.SlotRepository;
import com.example.parkingslot.entity.Slot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SlotServiceImpl implements SlotService{
    SlotRepository slotRepository;
    public Slot createSlot(Slot slot) throws Exception {
        return slotRepository.save(slot);
    }
}
