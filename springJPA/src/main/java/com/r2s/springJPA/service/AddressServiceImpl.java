package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.CreateAddressRequestDTO;
import com.r2s.springJPA.entity.Address;
import com.r2s.springJPA.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAllAddresses() {
        return (List<Address>) addressRepository.findAll();
    }

    @Override
    public Address insertAddress(CreateAddressRequestDTO requestDTO) {
        try {
            Address address = new Address();
            address.setName(requestDTO.getName());
            address.setPhone(requestDTO.getPhone());
            address.setDistrict(requestDTO.getDistrict());
            address.setProvince(requestDTO.getProvince());
            address.setStreet(requestDTO.getStreet());
            address.setType(requestDTO.isType());
            address.setDefaultAddress(requestDTO.isDefaultAddress());
            address.setUserId(requestDTO.getUserId());
            address.setDeleted(false);

            return addressRepository.save(address);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
