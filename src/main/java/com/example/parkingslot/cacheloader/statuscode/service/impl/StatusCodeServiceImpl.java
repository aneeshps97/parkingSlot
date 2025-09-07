package com.example.parkingslot.cacheloader.statuscode.service.impl;

import com.example.parkingslot.cacheloader.statuscode.entity.StatusCode;
import com.example.parkingslot.cacheloader.statuscode.repository.StatusCodeRepository;
import com.example.parkingslot.cacheloader.statuscode.service.StatusCodeService;
import com.example.parkingslot.exceptionhandler.ParkingSlotException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.parkingslot.constants.StatusCodes;

@Service
public class StatusCodeServiceImpl implements StatusCodeService {
    private final Map<Integer, String> statusCodeMap = new HashMap<>();
    @Autowired
    private StatusCodeRepository statusCodeRepository;

    @PostConstruct // Loads status codes into the map on startup
    public void loadStatusCodes() {
        List<StatusCode> statusCodes = statusCodeRepository.findAll();
        for (StatusCode statusCode : statusCodes) {
            statusCodeMap.put(statusCode.getCode(), statusCode.getMessage());
        }
    }

    public String getMessageForCode(int code) {
        // First, check in-memory map
        if(statusCodeMap.containsKey(code)){
            return statusCodeMap.get(code);
        }
        // If not found in map, check the database (optional)
        return statusCodeRepository.findByCode(code)
                .map(statusCode -> {
                    statusCodeMap.put(statusCode.getCode(), statusCode.getMessage()); // Cache it for later
                    return statusCode.getMessage();
                })
                .orElse("UNKNOWN_ERROR");

    }

    @Override
    public StatusCode add(StatusCode statusCode) throws ParkingSlotException {

        try {
            statusCode= statusCodeRepository.save(statusCode);
        }catch (Exception e){
            throw new ParkingSlotException(StatusCodes.UNABLE_TO_ADD_NEW_STATUS_CODE);
        }
        return statusCode;
    }

    @Override
    public List<StatusCode> findAll() throws ParkingSlotException {
        List<StatusCode> statusCodes= new ArrayList<>();
        try {
            statusCodes = statusCodeRepository.findAll();
        }catch (Exception e){
            throw new ParkingSlotException(StatusCodes.UNABLE_TO_FETCH_STATUS_CODES);
        }
        return statusCodes;
    }

    @Override
    public StatusCode update(int id, StatusCode statusCode) throws ParkingSlotException {
        try {
            StatusCode statusCode1 = statusCodeRepository.findById(id).orElseThrow(()->new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_STATUS_CODE));
            statusCode1.setCode(statusCode.getCode());
            statusCode1.setMessage(statusCode.getMessage());
            statusCodeRepository.save(statusCode1);
        }catch (Exception e){
            throw new ParkingSlotException(StatusCodes.UNABLE_TO_UPDATE_STATUS_CODES);
        }
        return statusCode;
    }

    @Override
    public StatusCode delete(int id) throws ParkingSlotException {
        StatusCode statusCode = null;
        try {
            statusCode = statusCodeRepository.findById(id).orElseThrow(()->new ParkingSlotException(StatusCodes.UNABLE_TO_FIND_STATUS_CODE));
            statusCodeRepository.delete(statusCode);
        }catch (Exception e){
            throw new ParkingSlotException(StatusCodes.UNABLE_TO_DELETE_STATUS_CODES);
        }
        return statusCode;
    }
}
