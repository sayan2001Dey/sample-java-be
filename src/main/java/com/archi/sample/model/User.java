package com.archi.sample.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "User")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(columnDefinition = "varchar(20) default 'No name'")
    private String name;

    @Column(columnDefinition = "varchar(60)", unique = true, nullable = false)
    private String email;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String password;
}
