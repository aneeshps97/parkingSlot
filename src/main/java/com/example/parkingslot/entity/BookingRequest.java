package com.example.parkingslot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BookingRequest {
    int userId;
    int parkingAreaId;
    private LocalDate date;
    int slotId;
}
