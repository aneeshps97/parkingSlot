package com.example.parkingslot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Slot {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int slotId;
    @Column
    String name;
    @ManyToOne
    @JoinColumn(name = "parking_area_id", nullable = false)
    @JsonBackReference
    private ParkingArea parkingArea;

}
