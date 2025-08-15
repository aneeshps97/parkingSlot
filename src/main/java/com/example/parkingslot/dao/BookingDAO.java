package com.example.parkingslot.dao;

import com.example.parkingslot.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingDAO extends JpaRepository<Booking,Integer> {
    Optional<Booking> findByUserIdAndDateAndPId(Integer userId, String date, Integer pId);
    List<Booking> findByUserIdAndPId(int userId,Integer pId);
    List<Booking> findByUserIdIsNullAndPId(Integer pId);
}
