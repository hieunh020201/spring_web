package com.r2s.springJPA.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "CART")
@Where(clause = "IS_DELETED = false")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @JoinColumn(name = "STATUS")
    private String status;

    @OneToMany(mappedBy = "cartLineItemId.cart")
    private List<CartLineItem> cartLineItems;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;

}
