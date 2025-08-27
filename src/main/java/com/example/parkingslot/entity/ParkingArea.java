package com.example.parkingslot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
public class ParkingArea {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int parkingAreaId;
    @Column
    String name;
    @Column
    int adminId;
    @OneToMany(mappedBy = "parkingArea", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Slot> slots;
    @ManyToMany
    @JoinTable(
            name = "parkingarea_user",
            joinColumns = @JoinColumn(name = "parking_area_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnoreProperties("parkingAreas")
    private List<User> users;

    @Override
    public String toString() {
        return "ParkingArea{" +
                "parkingAreaId=" + parkingAreaId +
                ", name='" + name + '\'' +
                ", adminId=" + adminId +
                ", slots=" + slots +
                ", users=" + users +
                '}';
    }
}
