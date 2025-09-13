package com.example.parkingslot.service;

import com.example.parkingslot.constants.StatusCodes;
import com.example.parkingslot.entity.*;
import com.example.parkingslot.exceptionhandler.ParkingSlotException;
import com.example.parkingslot.repository.BookingRepository;
import com.example.parkingslot.repository.ParkingAreaRepository;
import com.example.parkingslot.repository.SlotRepository;
import com.example.parkingslot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    BookingRepository bookingRepository;
    SlotRepository slotRepository;
    ParkingAreaRepository parkingAreaRepository;
    UserRepository userRepository;


    @Override
    public List<Booking> getBookingByUserParkingAndDate(int userId, String date, int parkingAreaId) throws ParkingSlotException {
        List<Booking> bookings= bookingRepository.findByParkingArea_ParkingAreaIdAndUser_UserIdAndDate(parkingAreaId,userId, LocalDate.parse(date));
        if (bookings.isEmpty()){
            throw new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_BOOKING);
        }
        return bookings;
    }

    @Override
    public List<Booking> getFreeSlotsInParkingArea(int parkingAreaId) throws ParkingSlotException {
        List<Booking> bookings= bookingRepository.findByParkingAreaParkingAreaIdAndUserIsNull(parkingAreaId);
        try {
            if (bookings.isEmpty()){
                throw new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_FREE_SLOT);
            }
        }catch (Exception e){
            throw new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_FREE_SLOT);
        }
        return bookings;
    }

    @Override
    public List<Booking> releaseSlot(int bookingId) throws ParkingSlotException {
        List<Booking> bookings = new ArrayList<>();
        try {
            Booking booking = bookingRepository.findById(bookingId).orElseThrow(()->new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_BOOKING));
            booking.setUser(null);
            booking=bookingRepository.save(booking);
            bookings.add(booking);
        }catch (Exception e){
            throw new ParkingSlotException(StatusCodes.UNABLE_TO_RELEASE_SLOT);
        }
        return bookings;
    }

    @Override
    public List<Booking> bookSlotForUser(int userId, int bookingId) throws ParkingSlotException {
        List<Booking> bookings = new ArrayList<>();
        try{
            Booking booking = bookingRepository.findById(bookingId).orElseThrow(()->new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_BOOKING));
            User user = userRepository.findById(userId).orElseThrow(()->new ParkingSlotException(StatusCodes.USER_NOT_FOUND));
            booking.setUser(user);
            booking=bookingRepository.save(booking);
            bookings.add(booking);
        }catch (Exception e){
            throw new ParkingSlotException(StatusCodes.UNABLE_TO_BOOK_SLOT);
        }
        return bookings;
    }

    @Override
    public List<Booking> getBookingByUserForParkingArea(int userId, int parkingAreaId) throws ParkingSlotException {
        List<Booking> bookings= bookingRepository.findByParkingArea_ParkingAreaIdAndUser_UserId(parkingAreaId,userId);
        if (bookings.isEmpty()){
            throw new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_BOOKING);
        }
        return bookings;
    }

    @Override
    public List<Booking> getBookingForParkingArea(int parkingAreaId) throws ParkingSlotException {
        List<Booking> bookings= bookingRepository.findByParkingArea_ParkingAreaId(parkingAreaId);
        if (bookings.isEmpty()){
            throw new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_BOOKING);
        }
        return bookings;
    }

    @Override
    public List<Booking> assignSlotsToUser(BookingRequest bookingRequest) throws ParkingSlotException {
        List<Booking> bookings = new ArrayList<>();
        Booking booking= new Booking();
        try {
            Slot slot = slotRepository.findById(bookingRequest.getSlotId()).orElseThrow(()->new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_PARKING_SLOT));
            booking.setSlot(slot);
            ParkingArea parkingArea = parkingAreaRepository.findById(bookingRequest.getParkingAreaId()).orElseThrow(()->new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_PARKING_AREA));
            booking.setParkingArea(parkingArea);
            booking.setDate(bookingRequest.getDate());
            User user = userRepository.findById(bookingRequest.getUserId()).orElseThrow(()->new ParkingSlotException(StatusCodes.USER_NOT_FOUND));
            booking.setUser(user);
            booking = bookingRepository.save(booking);
            bookings.add(booking);
            slot.getBookings().add(booking);  // modify the existing collection
            slotRepository.save(slot);
        }catch (Exception e){
            throw new ParkingSlotException(StatusCodes.ASSIGNING_SLOT_TO_USER_FAILED);
        }
       return bookings;
    }

    @Override
    public List<Booking> removeBooking(int bookingId) throws ParkingSlotException {
        List<Booking> bookings = new ArrayList<>();
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()->new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_BOOKING));
        bookingRepository.delete(booking);
        bookings = bookingRepository.findAll();
        return bookings;
    }

    @Override
    public List<Booking> autoAssignSlotsToUsers(AutoAssignRequest autoAssignRequest) throws ParkingSlotException {
        List<Booking> bookings = new ArrayList<>();
        try {
            //before assigning delete the existing entry for the parking area if any
            List<Booking> existingBookings = bookingRepository.findByParkingArea_ParkingAreaId(autoAssignRequest.getParkingAreaId());
            for (Booking booking:existingBookings){
                bookingRepository.deleteById(booking.getBookingId());
            }
            List<BookingRequest> bookingRequests = createRequest(autoAssignRequest);
            for (BookingRequest bookingRequest : bookingRequests) {
                bookings.addAll(assignSlotsToUser(bookingRequest));
            }
        } catch (Exception e) {
            throw new ParkingSlotException(StatusCodes.AUTO_ASSIGNING_FAILED);
        }
        return bookings;
    }

    private static List<BookingRequest> createRequest(AutoAssignRequest request) {
        List<BookingRequest> assignments = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = LocalDate.parse(request.getStartDate(), formatter);
        LocalDate end = LocalDate.parse(request.getEndDate(), formatter);

        List<Integer> userIds = request.getUserIds();
        List<Integer> slotIds = request.getSlotIds();
        int parkingAreaId = request.getParkingAreaId();
        int frequency = request.getFrequency();

        int totalDays = (int) ChronoUnit.DAYS.between(start, end) + 1;
        int totalBlocks = (int) Math.ceil((double) totalDays / frequency);

        int userIndex = 0;

        for (int block = 0; block < totalBlocks; block++) {
            // For each block of 'frequency' days
            for (int slotIndex = 0; slotIndex < slotIds.size(); slotIndex++) {
                if (userIndex >= userIds.size()) break; // No more users to assign

                int userId = userIds.get(userIndex++);
                int slotId = slotIds.get(slotIndex);

                for (int f = 0; f < frequency; f++) {
                    LocalDate currentDate = start.plusDays(block * frequency + f);
                    if (currentDate.isAfter(end)) break;

                    assignments.add(new BookingRequest(userId, parkingAreaId, currentDate, slotId));
                }
            }
        }

        return assignments;
    }


}
