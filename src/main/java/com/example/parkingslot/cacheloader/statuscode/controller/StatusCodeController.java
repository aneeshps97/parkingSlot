package com.example.parkingslot.cacheloader.statuscode.controller;

import com.example.parkingslot.cacheloader.statuscode.entity.StatusCode;
import com.example.parkingslot.cacheloader.statuscode.service.StatusCodeService;
import com.example.parkingslot.constants.StatusCodes;
import com.example.parkingslot.controller.AuthenticationController;
import com.example.parkingslot.entity.SignUp;
import com.example.parkingslot.entity.User;
import com.example.parkingslot.exceptionhandler.ParkingSlotException;
import com.example.parkingslot.response.GenerateResponse;
import com.example.parkingslot.response.Response;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StatusCodeController {
    StatusCodeService statusCodeService;
    GenerateResponse generateResponse;
    static final Logger logger = LoggerFactory.getLogger(StatusCodeController.class);
    @PostMapping("statusCode/add")
    public ResponseEntity<Response<StatusCode>> add(@RequestBody StatusCode request) throws ParkingSlotException {
        logger.info("Request received for adding statusCode ::{}",request);
        StatusCode statusCode = statusCodeService.add(request);
        return generateResponse.formatResponse(StatusCodes.STATUS_CODE_ADDED_SUCCESSFULLY,StatusCodes.SUCCESS,statusCode, HttpStatus.ACCEPTED);
    }

    @GetMapping("statusCode/findAll")
    public ResponseEntity<Response<List<StatusCode>>> findAll() throws ParkingSlotException {
        logger.info("Request received for finding all statusCodes");
        List<StatusCode> statusCodes = statusCodeService.findAll();
        return generateResponse.formatResponse(StatusCodes.FETCHING_STATUS_CODES_SUCCESS,StatusCodes.SUCCESS,statusCodes, HttpStatus.ACCEPTED);
    }

    @PutMapping("statusCode/update/{id}")
    public ResponseEntity<Response<StatusCode>> update(@PathVariable int id,@RequestBody StatusCode request) throws ParkingSlotException {
        logger.info("Request received for updating statusCode ::{}",request);
        StatusCode statusCode = statusCodeService.update(id,request);
        return generateResponse.formatResponse(StatusCodes.STATUS_CODE_UPDATE_SUCCESS,StatusCodes.SUCCESS,statusCode, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("statusCode/delete/{id}")
    public ResponseEntity<Response<StatusCode>> delete(@PathVariable int id) throws ParkingSlotException {
        logger.info("Request received for deleting statusCode ::{}",id);
        StatusCode statusCode = statusCodeService.delete(id);
        return generateResponse.formatResponse(StatusCodes.STATUS_CODE_DELETED_SUCCESSFULLY,StatusCodes.SUCCESS,statusCode, HttpStatus.ACCEPTED);
    }

}
