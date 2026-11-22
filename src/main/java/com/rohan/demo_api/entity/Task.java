package com.rohan.demo_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @ManyToOne  // This is read as -> Many Tasks are related to one Employee
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    // This relation means an employee can have many tasks but not the other eay around.

}
