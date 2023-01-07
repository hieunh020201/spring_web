package com.r2s.springJPA.controller;

import com.r2s.springJPA.dto.CreateAddressRequestDTO;
import com.r2s.springJPA.dto.CreateUserRequestDTO;
import com.r2s.springJPA.entity.Address;
import com.r2s.springJPA.entity.Users;
import com.r2s.springJPA.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "user/user-id/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<?> getALlAddress() {
        List<Address> users = addressService.getAllAddresses();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertAddress(@RequestBody CreateAddressRequestDTO requestDTO) {
        Address address = addressService.insertAddress(requestDTO);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

}
