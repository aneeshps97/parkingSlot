package com.example.parkingslot.repository;

import com.example.parkingslot.entity.Booking;
import com.example.parkingslot.exceptionhandler.ParkingSlotException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
  /*  Optional<Booking> findByUserIdAndDateAndPId(Integer userId, String date, Integer pId);
    List<Booking> findByUserIdAndPId(int userId,Integer pId);
    List<Booking> findByUserIdIsNullAndPId(Integer pId);*/
  List<Booking> findByParkingArea_ParkingAreaId(int parkingAreaId) throws ParkingSlotException;
  List<Booking> findByParkingAreaParkingAreaIdAndUserIsNull(Integer ParkingAreaId) throws ParkingSlotException;
  List<Booking> findByParkingArea_ParkingAreaIdAndUser_UserId(int parkingAreaId, int userId) throws ParkingSlotException;
  List<Booking> findByParkingArea_ParkingAreaIdAndUser_UserIdAndDate(int parkingAreaId, int userId, LocalDate date) throws ParkingSlotException;
}
