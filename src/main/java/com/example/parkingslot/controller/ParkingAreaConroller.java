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

    @PutMapping("/parkingArea/changeAdmin/{parkingAreaId}")
    public ResponseEntity<Response<ParkingArea>> changeAdmin(@PathVariable int parkingAreaId,@RequestParam int newAdminId) throws  ParkingSlotException{
        logger.info("Request received for changing the admin of parking area ::{} newAdminId::{}",parkingAreaId,newAdminId);
        ParkingArea parkingArea = parkingAreaService.changeAdmin(parkingAreaId,newAdminId);
        return generateResponse.formatResponse(StatusCodes.CHANGING_ADMIN_SUCCESS,StatusCodes.SUCCESS,parkingArea,HttpStatus.ACCEPTED);
    }

    @GetMapping("/parkingArea/viewParkingArea")
    public ResponseEntity<Response<ParkingArea>> viewParkingArea(@RequestParam int parkingAreaId) throws ParkingSlotException {
        logger.info("Request received for finding parking area ::{}", parkingAreaId);
        ParkingArea parkingArea = parkingAreaService.findParkingAreaById(parkingAreaId);
        return generateResponse.formatResponse(StatusCodes.PARKING_AREA_CREATED_SUCCESSFULLY, StatusCodes.SUCCESS, parkingArea, HttpStatus.ACCEPTED);
    }

    @PostMapping("/parkingArea/createParkingArea")
    public ResponseEntity<Response<ParkingArea>> createParkingArea(@RequestBody ParkingArea request) throws ParkingSlotException {
        logger.info("Request received for creating parking area ::{}", request);
        ParkingArea parkingArea = parkingAreaService.createParkingArea(request);
        return generateResponse.formatResponse(StatusCodes.PARKING_AREA_CREATED_SUCCESSFULLY, StatusCodes.SUCCESS, parkingArea, HttpStatus.ACCEPTED);
    }


    @PutMapping("/parkingArea/{id}/addUsers")
    public ResponseEntity<Response<ParkingArea>> addUsersToParkingArea(@PathVariable int id, @RequestBody List<Integer> users) {
        logger.info("Request received for addingUsers to parking area ::{}", users);
        ParkingArea parkingArea = parkingAreaService.addUsersToParkingArea(id, users);
        return generateResponse.formatResponse(StatusCodes.SUCCESSFULLY_ADDED_USER_TO_PARKING_AREA, StatusCodes.SUCCESS, parkingArea, HttpStatus.ACCEPTED);
    }

    @PutMapping("/parkingArea/{id}/addSlots")
    public ResponseEntity<Response<ParkingArea>> addSlotsToParkingArea(@PathVariable int id, @RequestBody List<Slot> slots) {
        logger.info("Request received for addingSlotsTo parking area ::{}", slots);
        ParkingArea parkingArea = parkingAreaService.addSlotsToParkingArea(id, slots);
        return generateResponse.formatResponse(StatusCodes.SUCCESSFULLY_ADDED_SLOTS_TO_PARKING_AREA, StatusCodes.SUCCESS, parkingArea, HttpStatus.ACCEPTED);
    }

    @PutMapping("/parkingArea/updateBasicDetails/{id}")
    public ResponseEntity<Response<ParkingArea>> updateBasicDetails(@PathVariable int id,
                                                            @RequestParam String newName,
                                                            @RequestParam String newTicketLine1,
                                                            @RequestParam String newTicketLine2) {
        logger.info("Request received for changing basic details for parking area id::{} name::{} ticketLine1::{} ticketLine2:{}", id, newName,newTicketLine1,newTicketLine2);
        ParkingArea parkingArea = parkingAreaService.updateBasicDetailsOfParkingArea(id, newName,newTicketLine1,newTicketLine2);
        return generateResponse.formatResponse(StatusCodes.SUCCESSFULLY_CHANGED_BASIC_DETAILS_OF_PARKING_AREA, StatusCodes.SUCCESS, parkingArea, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/parkingArea/deleteParkingArea/{parkingAreaId}")
    public ResponseEntity<Response<ParkingArea>> deleteParkingArea(@PathVariable int parkingAreaId) {
        logger.info("Request received for delete parking area id::{} ", parkingAreaId);
        ParkingArea parkingArea = parkingAreaService.deleteParkingArea(parkingAreaId);
        return generateResponse.formatResponse(StatusCodes.SUCCESSFULLY_CHANGED_BASIC_DETAILS_OF_PARKING_AREA, StatusCodes.SUCCESS, parkingArea, HttpStatus.ACCEPTED);
    }

    @PutMapping("/parkingArea/removeUserFromParkingArea/{parkingAreaId}")
    public ResponseEntity<Response<ParkingArea>> removeUserFromParkingArea(@PathVariable int parkingAreaId,
                                                                           @RequestParam int userId) {
        logger.info("Request received for removing user from parking area id::{} userId::{}", parkingAreaId, userId);
        ParkingArea parkingArea = parkingAreaService.removeUserFromParkingArea(parkingAreaId, userId);
        return generateResponse.formatResponse(StatusCodes.SUCCESSFULLY_CHANGED_BASIC_DETAILS_OF_PARKING_AREA, StatusCodes.SUCCESS, parkingArea, HttpStatus.ACCEPTED);
    }


}
