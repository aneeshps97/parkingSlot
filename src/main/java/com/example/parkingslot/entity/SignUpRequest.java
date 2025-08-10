package com.example.parkingslot.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
}
