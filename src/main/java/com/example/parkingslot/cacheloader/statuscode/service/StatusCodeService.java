package com.example.parkingslot.cacheloader.statuscode.service;

import com.example.parkingslot.cacheloader.statuscode.entity.StatusCode;
import com.example.parkingslot.constants.StatusCodes;
import com.example.parkingslot.exceptionhandler.ParkingSlotException;

import java.util.List;

public interface StatusCodeService {
    public String getMessageForCode(int code);
    public StatusCode add(StatusCode statusCode) throws ParkingSlotException;
    public List<StatusCode> findAll() throws ParkingSlotException;
    public StatusCode update(int id,StatusCode statusCode) throws ParkingSlotException;
    public StatusCode delete(int id) throws ParkingSlotException;
}
