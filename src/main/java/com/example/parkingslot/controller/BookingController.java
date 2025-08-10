package com.example.parkingslot.controller;

import com.example.parkingslot.entity.Booking;
import com.example.parkingslot.service.BookingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookingController {
    static  final Logger logger = LoggerFactory.getLogger(BookingController.class);
    BookingService bookingService;
    @GetMapping("/findSlot")
    public Booking findSlotByUserIdAndDate(
            @RequestParam int userId,
            @RequestParam String date
    ) throws Exception {
        Booking booking = null;
        booking = bookingService.findByUserIdAndDate(userId,date);
        return booking;
    }

    @GetMapping("/findSlots")
    public List<Booking> findSlotsByUserId(
            @RequestParam int userId
    ) throws Exception {
       List<Booking> bookings = null;
        bookings = bookingService.findByUserId(userId);
        return bookings;
    }

    @GetMapping("/freeSlots")
    public List<Booking> findFreeSlots() throws Exception {
        List<Booking> bookings = null;
        bookings = bookingService.findByUserIdIsNull();
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

}
