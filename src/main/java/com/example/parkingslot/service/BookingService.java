package com.example.parkingslot.service;

import com.example.parkingslot.entity.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    Booking findByUserIdAndDate(int userId, String date) throws Exception;
    List<Booking> findByUserId(int userId) throws Exception;
    List<Booking> findByUserIdIsNull() throws Exception;
    Booking releaseSlot(int userId) throws Exception;
    Booking bookFreeSlot(int userId, int slotId) throws Exception;
}
