package com.example.parkingslot.controller;

import com.example.parkingslot.entity.LoginRequest;
import com.example.parkingslot.entity.SignUpRequest;
import com.example.parkingslot.entity.User;
import com.example.parkingslot.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

public class AuthenticationController {
    static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    UserService userService;
    @PostMapping("/signUp")
    public User signUp(@RequestBody SignUpRequest request) {
        User user =null;
        try {
            user = userService.add(request);
        } catch (Exception e) {
            logger.info("Exception coming in adaptor");
            throw new RuntimeException(e);
        }

        return user;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/logIn")
    public User logIn(@RequestBody LoginRequest request) throws Exception {
        User user = userService.findByEmail(request.getEmail());
        if (user.getPassword().equalsIgnoreCase(request.getPassword()))
            return user;
        else return null;
    }

    @GetMapping("/test")
    public boolean getValueTest() {
        return true;
    }
}

