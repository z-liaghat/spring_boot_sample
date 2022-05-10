package com.example.springbootsample.auth.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name ="authorities")//, uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})} )
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String authority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user.username")
    private User user;

    }



