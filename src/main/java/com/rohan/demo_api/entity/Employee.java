package com.rohan.demo_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
// annotation for defining db entities (schemas).
@Getter
// all getters are made automatically.
@Data
// IMP -> modelMapper requires Getters, Setters and a no Arg Constructor for a class which is used as target
// in modelMapper.map(). @Data covers all of them. The no Arg Constructor comes from @RequiredArgsConstructor
// which is included in the @Data
@Builder
// eases the way to create objects.
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Column(unique = true)
    private String email;

    private int salary;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    // PERSIST -> When u want address to be automatically be saved and commited in the db when employee is created.
    // REMOVE -> When u want an employee's address to be automatically removed from the db when that employee is removed.
    // Pitfalls of CascadeType.REMOVE in -ToMany relations -> https://thorben-janssen.com/avoid-cascadetype-delete-many-assocations/
    @JoinColumn(name = "address_id")  // this is the owner side.
    // name in @JoinColumn will be the name of the column u want in the db.
    private Address address;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    // more about fetch on -> https://stackoverflow.com/questions/2990799/difference-between-fetchtype-lazy-and-eager-in-java-persistence-api
    // Defaults of fetch :- [IMP]
    // OneToOne & ManyToOne -> Eager
    // OneToMany & ManyToMany -> Lazy
    // IMP -> Know about N+1 query problem due to lazy fetching in N+1_query_problem.txt file present at root folder.
    private List<Task> tasks;
    // As this is the inverse side, no list of tasks are really stored here.
    // This will just be used for querying.

    @OneToOne
    @JoinColumn(name = "main_task")
    private Task mainTask;

    // Two entities can have multiple relations between them
    // Eg -> We have 2 relations between Employee and Task.

    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY)
    private Set<Team> teams;
}
