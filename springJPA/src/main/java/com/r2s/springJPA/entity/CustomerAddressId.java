package com.r2s.springJPA.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressId implements Serializable {
    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

}
