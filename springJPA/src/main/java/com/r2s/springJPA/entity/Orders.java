package com.r2s.springJPA.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "ORDERS")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CART_ID")
    private int cartId;

    @Column(name = "TOTAL_COST")
    private int totalCost;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;
}
