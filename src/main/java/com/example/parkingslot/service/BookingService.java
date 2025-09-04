package com.example.parkingslot.service;

import com.example.parkingslot.entity.Booking;
import com.example.parkingslot.entity.BookingRequest;
import com.example.parkingslot.exceptionhandler.ParkingSlotException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    List<Booking> getBookingByUserParkingAndDate(int userId, String date,int parkingAreaId) throws Exception;
    List<Booking> getFreeSlotsInParkingArea(int parkingAreaId) throws ParkingSlotException;
    List<Booking> releaseSlot(int userId) throws ParkingSlotException;
    List<Booking> bookSlotForUser(int userId, int bookingId) throws ParkingSlotException;
    List<Booking> getBookingByUserForParkingArea(int userId,int parkingAreaId) throws ParkingSlotException;
    List<Booking> getBookingForParkingArea(int parkingAreaId) throws ParkingSlotException;
    List<Booking> assignSlotsToUser(BookingRequest bookingRequest) throws ParkingSlotException;
    List<Booking> removeBooking(int bookingId) throws ParkingSlotException;


}
