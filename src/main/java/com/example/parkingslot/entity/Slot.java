package com.example.parkingslot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "slot", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Booking> bookings = new ArrayList<>();
}
