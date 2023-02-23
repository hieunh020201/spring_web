package com.r2s.springJPA.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "CART_LINE_ITEM")
@Where(clause = "IS_DELETED = false")
public class CartLineItem {

    @EmbeddedId
    private CartLineItemId cartLineItemId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "COST")
    private int cost;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;
}
