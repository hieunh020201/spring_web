package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CreateAddressByCustomerRequestDTO;
import com.r2s.springJPA.dto.request.CreateAddressRequestDto;
import com.r2s.springJPA.dto.request.UpdateAddressByCustomerRequestDto;
import com.r2s.springJPA.dto.request.UpdateAddressRequestDto;
import com.r2s.springJPA.dto.response.*;
import com.r2s.springJPA.entity.Address;
import com.r2s.springJPA.entity.Customer;
import com.r2s.springJPA.entity.CustomerAddress;
import com.r2s.springJPA.entity.CustomerAddressId;
import com.r2s.springJPA.mapper.AddressMapper;
import com.r2s.springJPA.mapper.CustomerAddressMapper;
import com.r2s.springJPA.mapper.CustomerMapper;
import com.r2s.springJPA.repository.AddressRepository;
import com.r2s.springJPA.repository.CustomerAddressRepository;
import com.r2s.springJPA.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerAddressRepository customerAddressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    CustomerAddressMapper customerAddressMapper;

    @Override
    public PageResponseDto getAllAddresses(Pageable pageable) {
        Page<Address> addressesPage = addressRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Can't get Users by paging"));

        PageResponseDto pageResponseDto = new PageResponseDto();
        pageResponseDto.setPage(addressesPage.getNumber());
        pageResponseDto.setSize(addressesPage.getSize());
        pageResponseDto.setTotalPages(addressesPage.getTotalPages());
        pageResponseDto.setTotalRecord(addressesPage.getTotalElements());
        List<AddressResponseDto> addressResponseDtos = addressMapper.convertEntitiesResponseDtos(addressesPage.getContent());
        pageResponseDto.setData(addressResponseDtos);
        return pageResponseDto;
    }

    @Override
    public AddressResponseDto insertAddress(CreateAddressRequestDto requestDto) {
        Address address = new Address();
        address.setDistrict(requestDto.getDistrict());
        address.setProvince(requestDto.getProvince());
        address.setStreet(requestDto.getStreet());
        address.setDeleted(false);
        return addressMapper.convertEntityResponseDto(addressRepository.save(address));
    }

    @Override
    public AddressResponseDto updateAddress(int addressId, UpdateAddressRequestDto requestDto) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> {
            throw new IllegalArgumentException("Address doesn't exist");
        });
        address.setDistrict(requestDto.getDistrict());
        address.setProvince(requestDto.getProvince());
        address.setStreet(requestDto.getStreet());

        return addressMapper.convertEntityResponseDto(addressRepository.save(address));
    }

    @Override
    public AddressResponseDto getAddressById(int addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> {
            throw new IllegalArgumentException("Address doesn't exist");
        });

        return addressMapper.convertEntityResponseDto(address);
    }

    @Override
    public void deleteAddress(int addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> {
            throw new IllegalArgumentException("Address doesn't exist");
        });
        address.setDeleted(true);
        addressRepository.save(address);
    }


    @Override
    @Transactional
    public CustomerAddressResponseDto insertAddressByCustomer(int customerId, CreateAddressByCustomerRequestDTO requestDTO) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("CustomerId is invalid"));

        Address address = addressRepository.findById(requestDTO.getAddressId())
                        .orElseThrow(() -> new IllegalArgumentException("AddressId is invalid"));

        CustomerAddressResponseDto customerAddressResponseDto = new CustomerAddressResponseDto();

        if (customerAddressRepository.findById(new CustomerAddressId(customer, address))
                .isPresent()) {
            new IllegalArgumentException("Customer already has this address");
        } else {
            CustomerAddress customerAddress = new CustomerAddress();
            customerAddress.setName(requestDTO.getName());
            customerAddress.setPhone(requestDTO.getPhone());
            customerAddress.setType(requestDTO.isType());
            customerAddress.setDefaultAddress(requestDTO.isDefaultAddress());
            customerAddress.setCustomerAddressId(new CustomerAddressId(customer, address));
            customerAddress.setDeleted(false);
            CustomerAddress customerAddressSaved = customerAddressRepository.save(customerAddress);

            customerAddressResponseDto = customerAddressMapper.convertEntityResponseDto(customerAddressSaved);
            AddressResponseDto addressResponseDto = addressMapper.convertEntityResponseDto(customerAddressSaved.getCustomerAddressId().getAddress());
            customerAddressResponseDto.setAddress(addressResponseDto);

        };

        return customerAddressResponseDto;
    }

    @Override
    public AddressByCustomerResponseDto getListAddressesByCustomer(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("CustomerId is invalid"));
        customer.getCustomerAddresses();

        AddressByCustomerResponseDto addressByCustomerResponseDto = new AddressByCustomerResponseDto();
        addressByCustomerResponseDto = customerMapper.convertEntityResponseDtoV2(customer);

        List<CustomerAddressResponseDto> customerAddressResponseDtos = customerAddressMapper.convertEntitiesResponseDtos(customer.getCustomerAddresses());

        addressByCustomerResponseDto.setAddresses(customerAddressResponseDtos);

        return addressByCustomerResponseDto;
    }

    @Override
    @Transactional
    public CustomerAddressResponseDto updateAddressByCustomer(int customerId, int addressId, UpdateAddressByCustomerRequestDto requestDto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("CustomerId is invalid"));

        CustomerAddress customerAddress = customer.getCustomerAddresses().stream().filter(x -> addressId == x.getCustomerAddressId().getAddress().getId())
                .findAny().orElseThrow(() -> new IllegalArgumentException("AddressId is invalid"));

        customerAddress.setName(requestDto.getName());
        customerAddress.setPhone(requestDto.getPhone());
        customerAddress.setType(requestDto.isType());
        customerAddress.setDefaultAddress(requestDto.isDefaultAddress());

        CustomerAddress customerAddressSaved = customerAddressRepository.save(customerAddress);


        CustomerAddressResponseDto customerAddressResponseDto = customerAddressMapper.convertEntityResponseDto(customerAddressSaved);
        AddressResponseDto addressResponseDto = addressMapper.convertEntityResponseDto(customerAddressSaved.getCustomerAddressId().getAddress());
        customerAddressResponseDto.setAddress(addressResponseDto);

        return customerAddressResponseDto;
    }

    @Override
    public void deleteAddressByCustomer(int customerId) {
//        Address address = addressRepository.findByCustomer(customerRepository.findById(customerId)
//                        .orElseThrow(() -> new IllegalArgumentException("CustomerId is invalid")))
//                .orElseThrow(() -> new IllegalArgumentException("Address doesn't exist"));

//        address.setDeleted(true);
//        addressRepository.save(address);
    }


}
