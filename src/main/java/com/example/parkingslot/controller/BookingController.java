package com.example.parkingslot.controller;

import com.example.parkingslot.entity.Booking;
import com.example.parkingslot.service.BookingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookingController {
    static  final Logger logger = LoggerFactory.getLogger(BookingController.class);
    BookingService bookingService;
    @GetMapping("/findSlot")
    public Booking findSlotByUserIdAndDateAndPId(
            @RequestParam int userId,
            @RequestParam String date,
            @RequestParam int pId
    ) throws Exception {
        Booking booking = null;
        booking = bookingService.findByUserIdAndDate(userId,date,pId);
        return booking;
    }

    @GetMapping("/findSlots")
    public List<Booking> findSlotsByUserId(
            @RequestParam int userId,
            @RequestParam int pId
    ) throws Exception {
       List<Booking> bookings = null;
        bookings = bookingService.findSlotsByUserIdAndPId(userId,pId);
        return bookings;
    }

    @GetMapping("/freeSlots")
    public List<Booking> findFreeSlotsByPId(
            @RequestParam int pId
    ) throws Exception {
        List<Booking> bookings = null;
        bookings = bookingService.findByUserIdIsNullAndPId(pId);
        return bookings;
    }

    @GetMapping("/bookFreeSlot")
    public Booking bookFreeSlot(
            @RequestParam int userId,
            @RequestParam int slotId
    ) throws Exception {
        Booking booking = null;
        booking = bookingService.bookFreeSlot(userId,slotId);
        return booking;
    }

    @GetMapping("/releaseSlot")
    public Booking releaseSlot(
            @RequestParam int slotId
    ) throws Exception {
        Booking booking = null;
        booking = bookingService.releaseSlot(slotId);
        return booking;
    }

    @PostMapping("/assignSlotsToUser")
    public Booking assignSlotsToUser(@RequestBody Booking booking
    ) throws Exception {
        logger.info("assigning slots to user ::{}",booking.toString());
        booking = bookingService.assignSlotsToUser(booking);
        return booking;
    }

}
