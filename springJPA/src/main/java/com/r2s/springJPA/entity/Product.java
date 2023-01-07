package com.r2s.springJPA.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "CATEGORY_ID")
    private int categoryId;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;
}
