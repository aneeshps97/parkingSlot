package com.example.parkingslot.service;

import com.example.parkingslot.dao.SlotDAO;
import com.example.parkingslot.entity.Slot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class SlotServiceImpl implements SlotService{
    SlotDAO slotDAO;
    public Slot createSlot(Slot slot) throws Exception {
        return slotDAO.save(slot);
    }
}
