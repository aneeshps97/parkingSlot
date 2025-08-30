package com.example.parkingslot.controller;

import com.example.parkingslot.constants.StatusCodes;
import com.example.parkingslot.entity.Booking;
import com.example.parkingslot.entity.BookingRequest;
import com.example.parkingslot.response.GenerateResponse;
import com.example.parkingslot.response.Response;
import com.example.parkingslot.service.BookingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookingController {
    static  final Logger logger = LoggerFactory.getLogger(BookingController.class);
    BookingService bookingService;
    GenerateResponse generateResponse;

    @PostMapping("booking/assignSlotsToUser")
    public ResponseEntity<Response<List<Booking>>> assignSlotsToUser(@RequestBody BookingRequest bookingRequest
    ) throws Exception {
        logger.info("assigning slots to user ::{}",bookingRequest.toString());
        List<Booking> bookings = bookingService.assignSlotsToUser(bookingRequest);
        return generateResponse.formatResponse(StatusCodes.SUCCESSFULLY_ASSIGNED_SLOT_TO_USER,StatusCodes.SUCCESS, bookings, HttpStatus.ACCEPTED);
    }


    @GetMapping("booking/by-date")
    public ResponseEntity<Response<List<Booking>>> getBookingByUserParkingAndDate(
            @RequestParam int userId,
            @RequestParam String date,
            @RequestParam int parkingAreaId
    ) throws Exception {
        List<Booking> bookings = bookingService.getBookingByUserParkingAndDate(userId, date, parkingAreaId);
        return generateResponse.formatResponse(StatusCodes.FOUND_BOOKING, StatusCodes.SUCCESS, bookings,HttpStatus.ACCEPTED);
    }

    @GetMapping("booking/by-user")
    public ResponseEntity<Response<List<Booking>>> getBookingByUserForParkingArea(
            @RequestParam int userId,
            @RequestParam int parkingAreaId
    ) throws Exception {
       List<Booking> bookings = null;
       bookings = bookingService.getBookingByUserForParkingArea(userId,parkingAreaId);
       return generateResponse.formatResponse(StatusCodes.FOUND_BOOKING, StatusCodes.SUCCESS, bookings,HttpStatus.ACCEPTED);
    }

    @GetMapping("/booking/getFreeSlots")
    public ResponseEntity<Response<List<Booking>>> getFreeSlotsInParkingArea(
            @RequestParam int parkingAreaId
    ) throws Exception {
        List<Booking> bookings = null;
        bookings = bookingService.getFreeSlotsInParkingArea(parkingAreaId);
        return generateResponse.formatResponse(StatusCodes.FOUND_FREE_SLOT, StatusCodes.SUCCESS, bookings,HttpStatus.ACCEPTED);
    }

    @PutMapping("/booking/bookSlotForUser")
    public ResponseEntity<Response<List<Booking>>> bookSlotForUser(
            @RequestParam int userId,
            @RequestParam int bookingId
    ) throws Exception {
        List<Booking> bookings = null;
        bookings = bookingService.bookSlotForUser(userId,bookingId);
        return generateResponse.formatResponse(StatusCodes.BOOKED_FREE_SLOT, StatusCodes.SUCCESS, bookings,HttpStatus.ACCEPTED);

    }

    @PutMapping("booking/release")
    public ResponseEntity<Response<List<Booking>>> releaseSlot(
            @RequestParam int bookingId
    ) throws Exception {
        logger.info("releasing the booking ::{}",bookingId);
        List<Booking> bookings = bookingService.releaseSlot(bookingId);
        return generateResponse.formatResponse(StatusCodes.SUCCESSFULLY_RELEASED_ASSIGNED_SLOTS,StatusCodes.SUCCESS, bookings, HttpStatus.ACCEPTED);
    }


}
