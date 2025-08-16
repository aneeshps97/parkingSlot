package com.example.parkingslot.service;

import com.example.parkingslot.repository.ParkingLotRepository;
import com.example.parkingslot.repository.ParkingLotSlotsRepository;
import com.example.parkingslot.repository.ParkingLotUserDAO;
import com.example.parkingslot.entity.ParkingLot;
import com.example.parkingslot.entity.ParkingAreaSlots;
import com.example.parkingslot.entity.ParkingAreaUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParkingAreaServieImpl implements ParkingAreaService{
    ParkingLotRepository parkingLotRepository;
    ParkingLotSlotsRepository parkingLotSlotsRepository;
    ParkingLotUserDAO parkingLotUserDAO;
    @Override
    public ParkingLot createParkingArea(ParkingLot parkingLot) throws Exception {
        return parkingLotRepository.save(parkingLot);
    }

    @Override
    public ParkingAreaSlots addSlotsToParkingArea(ParkingAreaSlots parkingAreaSlots) throws Exception {
        return parkingLotSlotsRepository.save(parkingAreaSlots);
    }

    @Override
    public ParkingAreaUser addUsersToParkingArea(ParkingAreaUser parkingAreaUser) throws Exception {
        return parkingLotUserDAO.save(parkingAreaUser);
    }


    @Override
    public List<ParkingLot> findParkingAreaByUser(int user_id) throws Exception{
        List<ParkingAreaUser> links = parkingLotUserDAO.findByUserId(user_id);
        List<Integer> parkingIds = links.stream()
                .map(ParkingAreaUser::getPid)
                .collect(Collectors.toList());
        List<ParkingLot> parkingAreasOfUser = parkingLotRepository.findAllById(parkingIds);
        return parkingAreasOfUser;
    }
}
