package com.example.parkingslot.controller;

import com.example.parkingslot.entity.ParkingLot;
import com.example.parkingslot.entity.ParkingAreaSlots;
import com.example.parkingslot.entity.ParkingAreaUser;
import com.example.parkingslot.service.ParkingAreaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ParkingAreaConroller {
    //create parking area
    // add users to parking area
    // create parking slots for the parking area
    // assingn users with parking area for a specific date

    ParkingAreaService parkingAreaService;

    @PostMapping("/createParkingArea")
    public ParkingLot createParkingAea(@RequestBody ParkingLot parkingLot) throws Exception {
        parkingLot = parkingAreaService.createParkingArea(parkingLot);
        return parkingLot;
    }

  @PostMapping("/addUserToParkingArea")
    public ParkingAreaUser addParkingAreaUser(@RequestBody ParkingAreaUser parkingAreaUser) throws Exception{
       parkingAreaUser = parkingAreaService.addUsersToParkingArea(parkingAreaUser);
        return parkingAreaUser;
    }

    @PostMapping("/addSlotsToParkingArea")
    public ParkingAreaSlots addSlotsToParkingArea(@RequestBody ParkingAreaSlots parkingAreaSlots) throws Exception{
        parkingAreaSlots = parkingAreaService.addSlotsToParkingArea(parkingAreaSlots);
        return parkingAreaSlots;
    }

   @GetMapping("/findParkingAreaAssignedToUser")
    public List<ParkingLot> findParkingAreaOfUser(
            @RequestParam int userId
    ) throws Exception {
        List<ParkingLot> parkingAreasOfUser = parkingAreaService.findParkingAreaByUser(userId);
        return  parkingAreasOfUser;
    }


}
