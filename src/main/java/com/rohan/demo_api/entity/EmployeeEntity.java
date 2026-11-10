package com.rohan.demo_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
// annotation for defining db entities (schemas).
@Getter
// all getters are made automatically.
@Data
// IMP -> modelMapper requires Getters, Setters and a no Arg Constructor for a class which is used as target
// in modelMapper.map(). @Data covers all of them. The no Arg Constructor comes from @RequiredArgsConstructor
// which is included in the @Data
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Column(unique = true)
    private String email;

    private int salary;
}
