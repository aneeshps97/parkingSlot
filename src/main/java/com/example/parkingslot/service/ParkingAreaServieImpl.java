package com.example.parkingslot.service;

import com.example.parkingslot.constants.StatusCodes;
import com.example.parkingslot.entity.Slot;
import com.example.parkingslot.entity.User;
import com.example.parkingslot.exceptionhandler.ParkingSlotException;
import com.example.parkingslot.repository.ParkingAreaRepository;
//import com.example.parkingslot.repository.ParkingLotSlotsRepository;
//import com.example.parkingslot.repository.ParkingLotUserDAO;
import com.example.parkingslot.entity.ParkingArea;
//import com.example.parkingslot.entity.ParkingAreaSlots;
//import com.example.parkingslot.entity.ParkingAreaUser;
import com.example.parkingslot.repository.SlotRepository;
import com.example.parkingslot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParkingAreaServieImpl implements ParkingAreaService{
    ParkingAreaRepository parkingAreaRepository;
    UserRepository userRepository;
    SlotRepository slotRepository;
    @Override
    public ParkingArea createParkingArea(ParkingArea parkingArea) throws ParkingSlotException {
        try {
            return parkingAreaRepository.save(parkingArea);
        }catch (Exception e){
            e.printStackTrace();
            throw new ParkingSlotException(StatusCodes.ERROR_CREATING_PARKING_AREA);
        }

    }

    @Override
    public ParkingArea addSlotsToParkingArea(int parkingAreaId, List<Slot> slots) throws ParkingSlotException {
        // 1. Fetch the ParkingArea by ID
        ParkingArea parkingArea = parkingAreaRepository.findById(parkingAreaId)
                .orElseThrow(() -> new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_PARKING_AREA));

        // 2. Associate each slot with the parking area
        for (Slot slot : slots) {
            slot.setParkingArea(parkingArea); // assuming Slot has a parkingArea field
        }
        // 3. Save all slots
        slotRepository.saveAll(slots);
        // 4. Optionally update the parking area with the new slots
        parkingArea.getSlots().addAll(slots); // assuming ParkingArea has a slots list
        parkingAreaRepository.save(parkingArea); // optional if cascade is enabled
        return parkingArea;
    }


    @Override
    public ParkingArea addUsersToParkingArea(int parkingAreaId, List<Integer> userIds) throws ParkingSlotException {
        try {
            // 1. Fetch the ParkingArea by ID
            ParkingArea parkingArea = parkingAreaRepository.findById(parkingAreaId)
                    .orElseThrow(() -> new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_PARKING_AREA));
            List<User> users= new ArrayList<>();
            for(int userId:userIds){
                User user = userRepository.findById(userId).orElseThrow(()->new ParkingSlotException(StatusCodes.USER_NOT_FOUND));
                user.getParkingAreas().add(parkingArea);
                users.add(user);
            }
            parkingArea.getUsers().addAll(users); // assuming ParkingArea has a users list
            parkingAreaRepository.save(parkingArea); // optional if cascade is enabled
            return parkingArea;
        }catch (Exception e){
            throw  new ParkingSlotException(StatusCodes.UNABLE_TO_ADD_USER_TO_PARKING_AREA);
        }
    }

    @Override
    public ParkingArea findParkingAreaById(int parkingAreaId) throws ParkingSlotException {
        return parkingAreaRepository.findById(parkingAreaId).orElseThrow(()->new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_PARKING_AREA));
    }


   /* @Override
    public ParkingAreaSlots addSlotsToParkingArea(ParkingAreaSlots parkingAreaSlots) throws Exception {
        return parkingLotSlotsRepository.save(parkingAreaSlots);
    }*/

    /*@Override
    public ParkingAreaUser addUsersToParkingArea(ParkingAreaUser parkingAreaUser) throws Exception {
        return parkingLotUserDAO.save(parkingAreaUser);
    }*/


   /* @Override
    public List<ParkingArea> findParkingAreaByUser(int user_id) throws Exception{
        List<ParkingAreaUser> links = parkingLotUserDAO.findByUserId(user_id);
        List<Integer> parkingIds = links.stream()
                .map(ParkingAreaUser::getPid)
                .collect(Collectors.toList());
        List<ParkingArea> parkingAreasOfUser = parkingAreaRepository.findAllById(parkingIds);
        return parkingAreasOfUser;
    }*/
}
