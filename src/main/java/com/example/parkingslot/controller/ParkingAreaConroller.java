package com.example.parkingslot.controller;

import com.example.parkingslot.constants.StatusCodes;
import com.example.parkingslot.entity.*;
import com.example.parkingslot.exceptionhandler.ParkingSlotException;
import com.example.parkingslot.response.GenerateResponse;
import com.example.parkingslot.response.Response;
import com.example.parkingslot.service.ParkingAreaService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ParkingAreaConroller {
    static final Logger logger = LoggerFactory.getLogger(ParkingAreaConroller.class);
    ParkingAreaService parkingAreaService;
    GenerateResponse generateResponse;

    @GetMapping("/parkingArea/viewParkingArea")
    public ResponseEntity<Response<ParkingArea>> viewParkingArea(@RequestParam int parkingAreaId) throws ParkingSlotException {
        logger.info("Request received for finding parking area ::{}",parkingAreaId);
        ParkingArea parkingArea = parkingAreaService.findParkingAreaById(parkingAreaId);
        return generateResponse.formatResponse(StatusCodes.PARKING_AREA_CREATED_SUCCESSFULLY,StatusCodes.SUCCESS, parkingArea, HttpStatus.ACCEPTED);
    }

    @PostMapping("/parkingArea/createParkingArea")
    public ResponseEntity<Response<ParkingArea>> createParkingArea(@RequestBody ParkingArea request) throws ParkingSlotException {
        logger.info("Request received for creating parking area ::{}",request);
        ParkingArea parkingArea = parkingAreaService.createParkingArea(request);
        return generateResponse.formatResponse(StatusCodes.PARKING_AREA_CREATED_SUCCESSFULLY,StatusCodes.SUCCESS, parkingArea, HttpStatus.ACCEPTED);
    }

    @PutMapping("/parkingArea/{id}/addSlots")
    public ResponseEntity<Response<ParkingArea>> addSlotsToParkingArea(@PathVariable int id, @RequestBody List<Slot> slots){
        logger.info("Request received for addingSlotsTo parking area ::{}",slots);
        ParkingArea parkingArea=parkingAreaService.addSlotsToParkingArea(id,slots);
        return generateResponse.formatResponse(StatusCodes.SUCCESSFULLY_ADDED_SLOTS_TO_PARKING_AREA,StatusCodes.SUCCESS, parkingArea, HttpStatus.ACCEPTED);
    }

    @PutMapping("/parkingArea/{id}/addUsers")
    public ResponseEntity<Response<ParkingArea>> addUsersToParkingArea(@PathVariable int id, @RequestBody List<Integer> userIds){
        logger.info("Request received for adding usersTo parking area ::{}",userIds);
        ParkingArea parkingArea=parkingAreaService.addUsersToParkingArea(id,userIds);
        logger.info("this is the parking area response"+parkingArea.toString());
        return generateResponse.formatResponse(StatusCodes.SUCCESSFULLY_ADDED_USER_TO_PARKING_AREA,StatusCodes.SUCCESS, parkingArea, HttpStatus.ACCEPTED);
    }



    /*@PutMapping("/parkingArea/{id}/assignUsers")
    public ResponseEntity<Response<ParkingArea>> assignUsers(@PathVariable Long id, @RequestBody List<User> users){

    }*/



    /*@PostMapping("/addUserToParkingArea")
    public ParkingAreaUser addParkingAreaUser(@RequestBody ParkingAreaUser parkingAreaUser) throws Exception{
       parkingAreaUser = parkingAreaService.addUsersToParkingArea(parkingAreaUser);
        return parkingAreaUser;
    }*/

    /*@PostMapping("/addSlotsToParkingArea")
    public ParkingAreaSlots addSlotsToParkingArea(@RequestBody ParkingAreaSlots parkingAreaSlots) throws Exception{
        parkingAreaSlots = parkingAreaService.addSlotsToParkingArea(parkingAreaSlots);
        return parkingAreaSlots;
    }*/

   /*@GetMapping("/findParkingAreaAssignedToUser")
    public List<ParkingArea> findParkingAreaOfUser(
            @RequestParam int userId
    ) throws Exception {
        List<ParkingArea> parkingAreasOfUser = parkingAreaService.findParkingAreaByUser(userId);
        return  parkingAreasOfUser;
    }*/


}
