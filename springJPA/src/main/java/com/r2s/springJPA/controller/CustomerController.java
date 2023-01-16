package com.r2s.springJPA.controller;

import com.r2s.springJPA.dto.request.CreateAddressByCustomerRequestDTO;
import com.r2s.springJPA.dto.request.CreateCustomerRequestDTO;
import com.r2s.springJPA.dto.request.UpdateAddressRequestDto;
import com.r2s.springJPA.dto.request.UpdateCustomerRequestDto;
import com.r2s.springJPA.dto.response.AddressResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.dto.response.CustomerResponseDto;
import com.r2s.springJPA.service.AddressService;
import com.r2s.springJPA.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<?> getALlCustomers(Pageable pageable) {
        PageResponseDto pageResponseDto = customerService.getAllCustomers(pageable);
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertCustomer(@RequestBody CreateCustomerRequestDTO requestDTO) {
        CustomerResponseDto customer = customerService.insertCustomer(requestDTO);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/{customer-id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("customer-id") int customerId, @RequestBody UpdateCustomerRequestDto requestDto) {
        CustomerResponseDto customerResponseDto = customerService.updateCustomer(customerId, requestDto);
        return new ResponseEntity<>(customerResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable("customer-id") int customerId) {
        customerService.deleteCustomer(customerId);
        StringBuilder response = new StringBuilder();
        response.append("Delete Customer Id: ").append(customerId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<?> getCustomerByCustomerId(@PathVariable("customer-id") int customerId) {
        CustomerResponseDto customerResponseDto = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customerResponseDto, HttpStatus.OK);
    }

    @PostMapping("/{customer-id}/address")
    public ResponseEntity<?> insertAddressByCustomer(@PathVariable("customer-id") int customerId, @RequestBody CreateAddressByCustomerRequestDTO requestDTO) {
        AddressResponseDto addressResponseDto = addressService.insertAddressByCustomer(customerId, requestDTO);
        return new ResponseEntity<>(addressResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{customer-id}/address")
    public ResponseEntity<?> getAddressByCustomer(@PathVariable("customer-id") int customerId) {
        CustomerResponseDto customerResponseDto = customerService.getCustomerById(customerId);
        AddressResponseDto addressResponseDto = addressService.getAddressByCustomer(customerId);
        customerResponseDto.setAddressResponseDto(addressResponseDto);
        return new ResponseEntity<>(customerResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{customer-id}/address")
    public ResponseEntity<?> updateAddressByCustomer(@PathVariable("customer-id") int customerId, @RequestBody UpdateAddressRequestDto requestDTO) {
        CustomerResponseDto customerResponseDto = customerService.getCustomerById(customerId);
        AddressResponseDto addressResponseDto = addressService.updateAddressByCustomer(customerId, requestDTO);
        customerResponseDto.setAddressResponseDto(addressResponseDto);
        return new ResponseEntity<>(customerResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{customer-id}/address")
    public ResponseEntity<?> deleteAddressByCustomer(@PathVariable("customer-id") int customerId) {
        addressService.deleteAddressByCustomer(customerId);
        StringBuilder response = new StringBuilder();
        response.append("Delete Address of Customer Id: ").append(customerId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
