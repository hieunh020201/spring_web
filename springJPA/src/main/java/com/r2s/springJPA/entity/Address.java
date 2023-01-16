package com.r2s.springJPA.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "PROVINCE")
    private String province;

    @Column(name = "DISTRICT")
    private String district;

    @Column(name = "STREET")
    private String street;

    @Column(name = "TYPE")
    private boolean type;

    @Column(name = "DEFAULT_ADDRESS")
    private boolean defaultAddress;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;

    @OneToOne
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    private Customer customer;
}
