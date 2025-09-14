package com.example.parkingslot.service;

import com.example.parkingslot.constants.StatusCodes;
import com.example.parkingslot.controller.AuthenticationController;
import com.example.parkingslot.entity.*;
import com.example.parkingslot.exceptionhandler.ParkingSlotException;
import com.example.parkingslot.repository.BookingRepository;
import com.example.parkingslot.repository.ParkingAreaRepository;
import com.example.parkingslot.repository.SlotRepository;
import com.example.parkingslot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);


    @Override
    public List<Booking> getBookingByUserParkingAndDate(int userId, String date, int parkingAreaId) throws ParkingSlotException {
        List<Booking> bookings = bookingRepository.findByParkingArea_ParkingAreaIdAndUser_UserIdAndDate(parkingAreaId, userId, LocalDate.parse(date));
        if (bookings.isEmpty()) {
            throw new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_BOOKING);
        }
        return bookings;
    }

    @Override
    public List<Booking> getFreeSlotsInParkingArea(int parkingAreaId) throws ParkingSlotException {
        List<Booking> bookings = bookingRepository.findByParkingAreaParkingAreaIdAndUserIsNull(parkingAreaId);
        try {
            if (bookings.isEmpty()) {
                throw new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_FREE_SLOT);
            }
        } catch (Exception e) {
            throw new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_FREE_SLOT);
        }
        return bookings;
    }

    @Override
    public List<Booking> releaseSlot(int bookingId) throws ParkingSlotException {
        List<Booking> bookings = new ArrayList<>();
        try {
            Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_BOOKING));
            booking.setUser(null);
            booking = bookingRepository.save(booking);
            bookings.add(booking);
        } catch (Exception e) {
            throw new ParkingSlotException(StatusCodes.UNABLE_TO_RELEASE_SLOT);
        }
        return bookings;
    }

    @Override
    public List<Booking> bookSlotForUser(int userId, int bookingId) throws ParkingSlotException {
        List<Booking> bookings = new ArrayList<>();
        try {
            Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_BOOKING));
            User user = userRepository.findById(userId).orElseThrow(() -> new ParkingSlotException(StatusCodes.USER_NOT_FOUND));
            booking.setUser(user);
            booking = bookingRepository.save(booking);
            bookings.add(booking);
        } catch (Exception e) {
            throw new ParkingSlotException(StatusCodes.UNABLE_TO_BOOK_SLOT);
        }
        return bookings;
    }

    @Override
    public List<Booking> getBookingByUserForParkingArea(int userId, int parkingAreaId) throws ParkingSlotException {
        List<Booking> bookings = bookingRepository.findByParkingArea_ParkingAreaIdAndUser_UserId(parkingAreaId, userId);
        if (bookings.isEmpty()) {
            throw new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_BOOKING);
        }
        return bookings;
    }

    @Override
    public List<Booking> getBookingForParkingArea(int parkingAreaId) throws ParkingSlotException {
        List<Booking> bookings = bookingRepository.findByParkingArea_ParkingAreaId(parkingAreaId);
        if (bookings.isEmpty()) {
            throw new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_BOOKING);
        }
        return bookings;
    }

    @Override
    public List<Booking> assignSlotsToUser(BookingRequest bookingRequest) throws ParkingSlotException {
        List<Booking> bookings = new ArrayList<>();
        Booking booking = new Booking();
        try {
            Slot slot = slotRepository.findById(bookingRequest.getSlotId()).orElseThrow(() -> new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_PARKING_SLOT));
            booking.setSlot(slot);
            ParkingArea parkingArea = parkingAreaRepository.findById(bookingRequest.getParkingAreaId()).orElseThrow(() -> new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_PARKING_AREA));
            booking.setParkingArea(parkingArea);
            booking.setDate(bookingRequest.getDate());
            User user = userRepository.findById(bookingRequest.getUserId()).orElseThrow(() -> new ParkingSlotException(StatusCodes.USER_NOT_FOUND));
            booking.setUser(user);
            booking = bookingRepository.save(booking);
            bookings.add(booking);
            slot.getBookings().add(booking);  // modify the existing collection
            slotRepository.save(slot);
        } catch (Exception e) {
            throw new ParkingSlotException(StatusCodes.ASSIGNING_SLOT_TO_USER_FAILED);
        }
        return bookings;
    }

    @Override
    public List<Booking> removeBooking(int bookingId) throws ParkingSlotException {
        List<Booking> bookings = new ArrayList<>();
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_BOOKING));
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
            for (Booking booking : existingBookings) {
                bookingRepository.deleteById(booking.getBookingId());
            }
            List<BookingRequest> bookingRequests = createRequest(autoAssignRequest);
            logger.info("Booking requests::"+bookingRequests.toString());

            for (BookingRequest bookingRequest : bookingRequests) {
                bookings.addAll(assignSlotsToUser(bookingRequest));
            }
        } catch (Exception e) {
            throw new ParkingSlotException(StatusCodes.AUTO_ASSIGNING_FAILED);
        }
        return bookings;
    }

    private List<BookingRequest> createRequest(AutoAssignRequest request) {
        List<BookingRequest> assignments = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = LocalDate.parse(request.getStartDate(), formatter);
        LocalDate end = LocalDate.parse(request.getEndDate(), formatter);

        //from the userList create a sublist and then assign the slots to each of the members in the list
        //repeat for each day and then increase the index for that list then the issue will be solved
        List<Integer> userList = request.getUserIds();
        List<Integer> slotIdList = request.getSlotIds();
        int frequency = request.getFrequency();
        int usersForTheDayIndex = 0;
        List<List<Integer>> listOfUsersForDay = generateBalancedSublists(userList, slotIdList.size());
        for (LocalDate date = start; !date.isAfter(end); ) {
            for (int i = 0; i < frequency &&!date.isAfter(end); i++) {
                List<Integer> subList = listOfUsersForDay.get(usersForTheDayIndex % listOfUsersForDay.size());
                for (int j=0;j<subList.size();j++) {
                    assignments.add(new BookingRequest(subList.get(j), request.getParkingAreaId(),date,slotIdList.get(j)));
                }
                date = date.plusDays(1);
            }
            usersForTheDayIndex++;
        }

        return assignments;
    }

    private <T> List<List<T>> generateBalancedSublists(List<T> list, int windowSize) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List must not be null or empty");
        }
        if (windowSize <= 0 || windowSize > list.size()) {
            if (windowSize > list.size()) {
                windowSize = list.size();
            } else {
                throw new IllegalArgumentException("Window size must must be grater than 0");
            }

        }

        List<List<T>> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            List<T> sub = new ArrayList<>();
            for (int j = 0; j < windowSize; j++) {
                sub.add(list.get((i + j) % list.size())); // wrap-around
            }
            result.add(sub);
        }

        return result;
    }


}
