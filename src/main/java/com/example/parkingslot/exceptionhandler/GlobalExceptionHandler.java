package com.example.parkingslot.exceptionhandler;

import com.example.parkingslot.cacheloader.statuscode.service.StatusCodeService;
import com.example.parkingslot.response.Response;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.parkingslot.constants.StatusCodes;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@AllArgsConstructor
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    StatusCodeService statusCodeService;
    public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
   @ExceptionHandler(ParkingSlotException.class)
    public <T> ResponseEntity<Response<T>> handleException(ParkingSlotException e){
        Response<T> response = new Response<>();
        response.setStatusCode(e.getErrorCode());
        response.setStatus(StatusCodes.FAIL);
        response.setMessage(statusCodeService.getMessageForCode(e.getErrorCode()));
        return new ResponseEntity<Response<T>>(response, HttpStatus.BAD_REQUEST);
    }

   @ExceptionHandler(Exception.class)
    public <T> ResponseEntity<Response<T>> handleGeneralException(Exception e) {
        logger.error("Unhandled exception: {}", e.getMessage(), e);
        Response<T> response = new Response<>();
        response.setStatusCode(StatusCodes.GENERAL_EXCEPTION);
        response.setStatus(StatusCodes.FAIL);
        response.setMessage(statusCodeService.getMessageForCode(StatusCodes.GENERAL_EXCEPTION));
        return new ResponseEntity<Response<T>>(response, HttpStatus.BAD_REQUEST);
    }
}
