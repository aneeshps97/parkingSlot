package com.example.parkingslot.service;

import com.example.parkingslot.dao.ParkingAreaDao;
import com.example.parkingslot.dao.ParkingAreaSlotsDAO;
import com.example.parkingslot.dao.ParkingAreaUserDAO;
import com.example.parkingslot.entity.ParkingArea;
import com.example.parkingslot.entity.ParkingAreaSlots;
import com.example.parkingslot.entity.ParkingAreaUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParkingAreaServieImpl implements ParkingAreaService{
    ParkingAreaDao parkingAreaDao;
    ParkingAreaSlotsDAO parkingAreaSlotsDAO;
    ParkingAreaUserDAO parkingAreaUserDAO;
    @Override
    public ParkingArea createParkingArea(ParkingArea parkingArea) throws Exception {
        return parkingAreaDao.save(parkingArea);
    }

    @Override
    public ParkingAreaSlots addSlotsToParkingArea(ParkingAreaSlots parkingAreaSlots) throws Exception {
        return parkingAreaSlotsDAO.save(parkingAreaSlots);
    }

    @Override
    public ParkingAreaUser addUsersToParkingArea(ParkingAreaUser parkingAreaUser) throws Exception {
        return parkingAreaUserDAO.save(parkingAreaUser);
    }


    @Override
    public List<ParkingArea> findParkingAreaByUser(int user_id) throws Exception{
        List<ParkingAreaUser> links = parkingAreaUserDAO.findByUserId(user_id);
        List<Integer> parkingIds = links.stream()
                .map(ParkingAreaUser::getPid)
                .collect(Collectors.toList());
        List<ParkingArea> parkingAreasOfUser = parkingAreaDao.findAllById(parkingIds);
        return parkingAreasOfUser;
    }
}
