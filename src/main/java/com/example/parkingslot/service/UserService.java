package com.example.parkingslot.service;

import com.example.parkingslot.entity.Login;
import com.example.parkingslot.entity.SignUp;
import com.example.parkingslot.entity.User;
import com.example.parkingslot.exceptionhandler.ParkingSlotException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User add(SignUp request) throws ParkingSlotException;
    public User getUserToken(User user) throws Exception;
    public User findByEmail(String email) throws ParkingSlotException;
    public User authenticateUser(Login request) throws ParkingSlotException;
}
