package com.r2s.springJPA.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "USER_ADDRESS")
@Where(clause = "IS_DELETED = false")
public class UserAddress {

    @EmbeddedId
    private UserAddressId userAddressId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "TYPE")
    private boolean type;

    @Column(name = "DEFAULT_ADDRESS")
    private boolean defaultAddress;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;
}
