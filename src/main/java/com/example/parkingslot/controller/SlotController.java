package com.example.parkingslot.controller;

import com.example.parkingslot.constants.StatusCodes;
import com.example.parkingslot.entity.Slot;
import com.example.parkingslot.response.GenerateResponse;
import com.example.parkingslot.response.Response;
import com.example.parkingslot.service.ParkingAreaService;
import com.example.parkingslot.service.SlotService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SlotController {
    SlotService slotService;
    static final Logger logger = LoggerFactory.getLogger(ParkingAreaConroller.class);
    GenerateResponse generateResponse;
    @PostMapping("/createSlot")
    public Slot addSlots(@RequestBody Slot slot) throws Exception {
        slot = slotService.createSlot(slot);
        return slot;
    }

    @DeleteMapping("slot/deleteSlot/{slotId}")
    public ResponseEntity<Response<Slot>> deleteSlot(@PathVariable int slotId) throws Exception {
        slotService.deleteSlot(slotId);
        return generateResponse.formatResponse(StatusCodes.PARKING_SLOT_DELETED_SUCCESSFULLY,StatusCodes.SUCCESS, null, HttpStatus.ACCEPTED);
    }

    @PutMapping("slot/updateSlot/{slotId}")
    public ResponseEntity<Response<Slot>> updateSlot(@PathVariable int slotId,@RequestBody Slot slot) throws Exception {
        slot = slotService.updateSlot(slotId,slot);
        return generateResponse.formatResponse(StatusCodes.PARKING_SLOT_UPDATED_SUCCESSFULLY,StatusCodes.SUCCESS, slot, HttpStatus.ACCEPTED);
    }
}
