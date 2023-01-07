package com.r2s.springJPA.controller;

import com.r2s.springJPA.dto.request.CreateAddressByCustomerRequestDTO;
import com.r2s.springJPA.dto.request.CreateAddressRequestDto;
import com.r2s.springJPA.dto.request.UpdateAddressRequestDto;
import com.r2s.springJPA.dto.response.AddressResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.entity.Address;
import com.r2s.springJPA.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<?> getALlAddresses(Pageable pageable) {
        PageResponseDto pageResponseDto = addressService.getAllAddresses(pageable);
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertAddress(@RequestBody CreateAddressRequestDto requestDTO) {
        AddressResponseDto responseDto = addressService.insertAddress(requestDTO);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{address-id}")
    public ResponseEntity<?> updateAddress(@PathVariable("address-id") int addressId, @RequestBody UpdateAddressRequestDto requestDto) {
        AddressResponseDto responseDto = addressService.updateAddress(addressId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{address-id}")
    public ResponseEntity<?> getAddressByAddressId(@PathVariable("address-id") int addressId) {
        AddressResponseDto responseDto = addressService.getAddressById(addressId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{address-id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("address-id") int addressId) {
        addressService.deleteAddress(addressId);
        StringBuilder response = new StringBuilder();
        response.append("Delete Address Id: ").append(addressId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
