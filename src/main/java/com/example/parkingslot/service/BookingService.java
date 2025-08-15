package com.example.parkingslot.service;

import com.example.parkingslot.entity.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    Booking findByUserIdAndDate(int userId, String date,int pId) throws Exception;
    List<Booking> findSlotsByUserIdAndPId(int userId,int pId) throws Exception;
    List<Booking> findByUserIdIsNullAndPId(int pId) throws Exception;
    Booking releaseSlot(int userId) throws Exception;
    Booking bookFreeSlot(int userId, int slotId) throws Exception;
    Booking assignSlotsToUser(Booking booking) throws Exception;
}
