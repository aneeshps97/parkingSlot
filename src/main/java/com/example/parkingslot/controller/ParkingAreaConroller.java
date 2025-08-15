package com.example.parkingslot.controller;

import com.example.parkingslot.entity.ParkingArea;
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
    public ParkingArea createParkingAea(@RequestBody ParkingArea parkingArea) throws Exception {
        parkingArea = parkingAreaService.createParkingArea(parkingArea);
        return parkingArea;
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
    public List<ParkingArea> findParkingAreaOfUser(
            @RequestParam int userId
    ) throws Exception {
        List<ParkingArea> parkingAreasOfUser = parkingAreaService.findParkingAreaByUser(userId);
        return  parkingAreasOfUser;
    }


}
