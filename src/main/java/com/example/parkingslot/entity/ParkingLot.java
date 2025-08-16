package com.example.parkingslot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parking_area")
@Getter
@Setter
public class ParkingLot {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int p_id;
    @Column
    String name;
    @Column
    int admin_id;
}
