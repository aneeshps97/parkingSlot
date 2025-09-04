package com.example.parkingslot.exceptionhandler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class ParkingSlotException extends RuntimeException  {
    public ParkingSlotException(){}
    int errorCode;
    public ParkingSlotException(int errorCode){
        this.errorCode = errorCode;
    }
}
