package com.r2s.springJPA.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;
}
