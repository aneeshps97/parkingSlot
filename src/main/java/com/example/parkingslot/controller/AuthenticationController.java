package com.example.parkingslot.controller;

import com.example.parkingslot.constants.StatusCodes;
import com.example.parkingslot.entity.Login;
import com.example.parkingslot.entity.ParkingArea;
import com.example.parkingslot.entity.SignUp;
import com.example.parkingslot.entity.User;
import com.example.parkingslot.exceptionhandler.ParkingSlotException;
import com.example.parkingslot.response.GenerateResponse;
import com.example.parkingslot.response.Response;
import com.example.parkingslot.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    UserService userService;
    GenerateResponse generateResponse;
    @PostMapping("/signUp")
    public ResponseEntity<Response<User>> signUp(@RequestBody SignUp request) throws ParkingSlotException {
        logger.info("Request received for signUp ::{}",request);
        User user = userService.add(request);
        return generateResponse.formatResponse(StatusCodes.USER_CREATED_SUCCESSFULLY,StatusCodes.SUCCESS,user, HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/logIn")
    public ResponseEntity<Response<User>> logIn(@RequestBody Login request) throws ParkingSlotException {
        logger.info("Request received for login ::{}",request);
        User user = userService.authenticateUser(request);
        return generateResponse.formatResponse(StatusCodes.USER_CREATED_SUCCESSFULLY,StatusCodes.SUCCESS,user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/findUserByEmail")
    public ResponseEntity<Response<User>> findUserByEmail(@RequestParam String email) throws ParkingSlotException {
        logger.info("Request received for finding user by email ::{}",email);
        User user = userService.findByEmail(email);
        return generateResponse.formatResponse(StatusCodes.USER_FOUND,StatusCodes.SUCCESS,user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/findUserById")
    public ResponseEntity<Response<User>> findUserById(@RequestParam int userId) throws ParkingSlotException {
        logger.info("Request received for finding user by UserId ::{}",userId);
        User user = userService.findById(userId);
        return generateResponse.formatResponse(StatusCodes.USER_FOUND,StatusCodes.SUCCESS,user, HttpStatus.ACCEPTED);
    }



    @GetMapping("/test")
    public boolean getValueTest() {
        return true;
    }
}

