package com.r2s.springJPA.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CUSTOMER_ID")
    private int customerId;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;

}
