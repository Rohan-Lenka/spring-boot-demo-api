package com.rohan.demo_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String city;

    @OneToOne(mappedBy = "address")
    private Employee employee;  // this is the inverse side
    // The ultimate source of truth is the Employee class's address_id and not the other way around.
    // mappedBy = "<value>" where this value is the name of variable which contains the address_id in the owner side.
    // NOTE -> In the inverse side, no such column is made, this is just for helping when info will be queried.
}
