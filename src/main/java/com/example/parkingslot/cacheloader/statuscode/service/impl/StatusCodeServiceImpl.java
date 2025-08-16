package com.example.parkingslot.cacheloader.statuscode.service.impl;

import com.example.parkingslot.cacheloader.statuscode.entity.StatusCode;
import com.example.parkingslot.cacheloader.statuscode.repository.StatusCodeRepository;
import com.example.parkingslot.cacheloader.statuscode.service.StatusCodeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
