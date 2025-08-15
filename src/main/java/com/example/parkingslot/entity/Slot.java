package com.example.parkingslot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "slot")
@Getter
@Setter
public class Slot {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int s_id;
    @Column
    String name;
}
