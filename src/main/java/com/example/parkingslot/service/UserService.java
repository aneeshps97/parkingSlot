package com.example.parkingslot.service;

import com.example.parkingslot.entity.SignUpRequest;
import com.example.parkingslot.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User add(SignUpRequest request) throws Exception;
    public User getUserToken(User user) throws Exception;
    public User findByEmail(String email) throws Exception;
}
