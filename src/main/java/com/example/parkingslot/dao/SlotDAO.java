package com.example.parkingslot.dao;

import com.example.parkingslot.entity.Slot;
import com.example.parkingslot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotDAO extends JpaRepository<Slot,Integer> {
}
