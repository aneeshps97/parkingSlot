package com.example.parkingslot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bookings",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    @Column(nullable = true)
    Integer  userId;
    @Column
    String slotNo;
    @Column
    String date;
}
