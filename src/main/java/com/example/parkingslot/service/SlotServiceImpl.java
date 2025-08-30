package com.example.parkingslot.service;

import com.example.parkingslot.constants.StatusCodes;
import com.example.parkingslot.exceptionhandler.ParkingSlotException;
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

    @Override
    public void deleteSlot(int slotId) throws ParkingSlotException {
        slotRepository.delete(slotRepository.findById(slotId).orElseThrow(()->new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_PARKING_SLOT)));
    }

    @Override
    public Slot updateSlot(int slotId, Slot slot) throws ParkingSlotException {
        Slot slot1 = slotRepository.findById(slotId).orElseThrow(()->new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_PARKING_SLOT));
        slot1.setName(slot.getName());
        return slotRepository.save(slot1);
    }
}
