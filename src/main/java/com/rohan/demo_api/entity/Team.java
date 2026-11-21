package com.rohan.demo_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @ManyToMany
    @JoinTable( // ManyToMany relation requires to make join table in db, @JoinTable is used to configure it from the owner side.
            name = "team_employee",
            joinColumns = @JoinColumn(name = "team_id"), // name of the owner side FK in the join table.
            inverseJoinColumns = @JoinColumn(name = "employee_id"), // name of the inverse side FK in the join table.
            uniqueConstraints = @UniqueConstraint(columnNames = {"team_id", "employee_id"})
    )
    private Set<Employee> employees;

    // Note -> What happens when the join table should also have some other columns as well ?
    // Eg -> User and Post table, user rates a post, we want a Rating table having the FK of user, post and a rating.
    // U can't have a rating column in a join table made via above way.

    // 2 solutions ->
    // 1) https://www.baeldung.com/jpa-many-to-many (This is a bit complex).
    // 2) Manually make a join table (This is much simpler). Like this ->
    // @Entity
    // class User {
    //    // PK
    //    private String id;
    //
    //    // other columns
    //
    //    @OneToMany(mappedBy = "user")
    //    private Set<Post> ratedPosts;
    // }
    // @Entity
    // class Post {
    //    // PK
    //    private String id;
    //
    //    // other columns
    //
    //    @OneToMany(mappedBy = "post")
    //    private Set<User> raters;
    // }
    // @Entity
    // class Rating {
    //    // PK
    //    private String id;
    //
    //    @ManyToOne
    //    private User user;
    //
    //    @ManyToOne
    //    private Post post;
    //
    //    private int ratingValue;
    // }
}

