package com.example.parkingslot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ParkingAreaSlots")
@Getter
@Setter
public class ParkingAreaSlots {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    @Column
    int p_id;
    @Column
    String s_name;
}
