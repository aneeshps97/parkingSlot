package com.example.parkingslot.service;

import com.example.parkingslot.dao.BookingDAO;
import com.example.parkingslot.entity.Booking;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    BookingDAO bookingDAO;
    @Override
    public Booking findByUserIdAndDate(int userId, String date, int pId) {
        return (Booking) bookingDAO.findByUserIdAndDateAndPId(userId, date,pId)
                .orElseThrow(() -> new EntityNotFoundException("Slot not found for user " + userId + " on " + date +"in"+pId));
    }

    @Override
    public List<Booking> findSlotsByUserIdAndPId(int userId,int pId) throws Exception {
        List<Booking> bookings = bookingDAO.findByUserIdAndPId(userId,pId);
        if (bookings.isEmpty()) {
            throw new Exception("No slots found for user " + userId+" pId :"+pId);
        }
        return bookings;
    }

    @Override
    public List<Booking> findByUserIdIsNullAndPId(int pId) throws Exception {
        List<Booking> unclaimedBookings = bookingDAO.findByUserIdIsNullAndPId(pId);
        return unclaimedBookings;

    }

    @Transactional
    public Booking releaseSlot(int slotId) throws Exception {
        Booking booking = bookingDAO.findById(slotId)
                .orElseThrow(() -> new Exception("Slot not found with id: " + slotId));
        booking.setUserId(null);  // free the slot
        return bookingDAO.save(booking);
    }


    @Override
    public Booking bookFreeSlot(int userId, int slotId) throws Exception {
        Booking booking = bookingDAO.findById(slotId)
                .orElseThrow(() -> new Exception("Slot not found with id: " + slotId));
        booking.setUserId(userId);  // free the slot
        return bookingDAO.save(booking);
    }

    @Override
    public Booking assignSlotsToUser(Booking booking) throws Exception {
        return bookingDAO.save(booking);
    }




}
