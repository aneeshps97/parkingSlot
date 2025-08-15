package com.example.parkingslot.controller;

import com.example.parkingslot.entity.Slot;
import com.example.parkingslot.service.SlotService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SlotController {
    SlotService slotService;
    @PostMapping("/createSlot")
    public Slot addSlots(@RequestBody Slot slot) throws Exception {
        slot = slotService.createSlot(slot);
        return slot;
    }
}
