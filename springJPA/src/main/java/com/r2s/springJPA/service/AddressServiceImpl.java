package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CreateAddressByCustomerRequestDTO;
import com.r2s.springJPA.dto.request.CreateAddressRequestDto;
import com.r2s.springJPA.dto.request.UpdateAddressRequestDto;
import com.r2s.springJPA.dto.response.AddressResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.entity.Address;
import com.r2s.springJPA.mapper.AddressMapper;
import com.r2s.springJPA.repository.AddressRepository;
import com.r2s.springJPA.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressMapper addressMapper;

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
        if (!addressRepository.findByCustomer(customerRepository.findById(requestDto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("CustomerId is invalid")))
                .isEmpty()) {
            throw new IllegalArgumentException("Customer had address");
        }
        Address address = new Address();
        address.setName(requestDto.getName());
        address.setPhone(requestDto.getPhone());
        address.setDistrict(requestDto.getDistrict());
        address.setProvince(requestDto.getProvince());
        address.setStreet(requestDto.getStreet());
        address.setType(requestDto.isType());
        address.setDefaultAddress(requestDto.isDefaultAddress());
        address.setCustomer(customerRepository.findById(requestDto.getCustomerId()).get());
        address.setDeleted(false);
        return addressMapper.convertEntityResponseDto(addressRepository.save(address));
    }

    @Override
    public AddressResponseDto updateAddress(int addressId, UpdateAddressRequestDto requestDto) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> {
            throw new IllegalArgumentException("Address doesn't exist");
        });
        address.setName(requestDto.getName());
        address.setPhone(requestDto.getPhone());
        address.setDistrict(requestDto.getDistrict());
        address.setProvince(requestDto.getProvince());
        address.setStreet(requestDto.getStreet());
        address.setType(requestDto.isType());
        address.setDefaultAddress(requestDto.isDefaultAddress());

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
    public AddressResponseDto insertAddressByCustomer(int customerId, CreateAddressByCustomerRequestDTO requestDTO) {
            Address address = new Address();
            address.setName(requestDTO.getName());
            address.setPhone(requestDTO.getPhone());
            address.setDistrict(requestDTO.getDistrict());
            address.setProvince(requestDTO.getProvince());
            address.setStreet(requestDTO.getStreet());
            address.setType(requestDTO.isType());
            address.setDefaultAddress(requestDTO.isDefaultAddress());
            address.setCustomer(customerRepository.findById(customerId)
                    .orElseThrow(() -> new IllegalArgumentException("CustomerId is invalid")));
            address.setDeleted(false);

            return addressMapper.convertEntityResponseDto(addressRepository.save(address));
    }

    @Override
    public AddressResponseDto getAddressByCustomer(int customerId) {
        Optional<Address> address = addressRepository.findByCustomer(customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("CustomerId is invalid")));

        return addressMapper.convertEntityResponseDto(address.get());
    }

    @Override
    public AddressResponseDto updateAddressByCustomer(int customerId, UpdateAddressRequestDto requestDto) {
        Address address = addressRepository.findByCustomer(customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("CustomerId is invalid")))
                .orElseThrow(() -> new IllegalArgumentException("Address doesn't exist"));

        address.setName(requestDto.getName());
        address.setPhone(requestDto.getPhone());
        address.setProvince(requestDto.getProvince());
        address.setDistrict(requestDto.getDistrict());
        address.setStreet(requestDto.getStreet());
        address.setType(requestDto.isType());
        address.setDefaultAddress(requestDto.isDefaultAddress());
        return addressMapper.convertEntityResponseDto(addressRepository.save(address));
    }

    @Override
    public void deleteAddressByCustomer(int customerId) {
        Address address = addressRepository.findByCustomer(customerRepository.findById(customerId)
                        .orElseThrow(() -> new IllegalArgumentException("CustomerId is invalid")))
                .orElseThrow(() -> new IllegalArgumentException("Address doesn't exist"));

        address.setDeleted(true);
        addressRepository.save(address);
    }


}
