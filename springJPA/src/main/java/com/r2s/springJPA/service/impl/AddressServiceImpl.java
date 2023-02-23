package com.r2s.springJPA.service.impl;

import com.r2s.springJPA.dto.request.CreateAddressByUserRequestDTO;
import com.r2s.springJPA.dto.request.CreateAddressRequestDto;
import com.r2s.springJPA.dto.request.UpdateAddressByUserRequestDto;
import com.r2s.springJPA.dto.request.UpdateAddressRequestDto;
import com.r2s.springJPA.dto.response.*;
import com.r2s.springJPA.entity.Address;
import com.r2s.springJPA.entity.User;
import com.r2s.springJPA.entity.UserAddress;
import com.r2s.springJPA.entity.UserAddressId;
import com.r2s.springJPA.mapper.AddressMapper;
import com.r2s.springJPA.mapper.UserAddressMapper;
import com.r2s.springJPA.mapper.UserMapper;
import com.r2s.springJPA.repository.AddressRepository;
import com.r2s.springJPA.repository.UserAddressRepository;
import com.r2s.springJPA.repository.UserRepository;
import com.r2s.springJPA.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    UserAddressMapper userAddressMapper;

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
    public UserAddressResponseDto insertAddressByUser(int userId, CreateAddressByUserRequestDTO requestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User doesn't exist"));

        Address address = addressRepository.findById(requestDTO.getAddressId())
                        .orElseThrow(() -> new IllegalArgumentException("Address doesn't exist"));

        UserAddress userAddress = new UserAddress();

        if (userAddressRepository.existsById(new UserAddressId(user, address))) {
            throw new IllegalArgumentException("User already has this address");
        } else {
            userAddress.setUserAddressId(new UserAddressId(user, address));
        }

        userAddress.setName(requestDTO.getName());
        userAddress.setPhone(requestDTO.getPhone());
        userAddress.setType(requestDTO.isType());
        userAddress.setDefaultAddress(requestDTO.isDefaultAddress());
        userAddress.setDeleted(false);
        UserAddress userAddressSaved = userAddressRepository.save(userAddress);
        UserAddressResponseDto userAddressResponseDto = userAddressMapper.convertEntityResponseDto(userAddressSaved);
        AddressResponseDto addressResponseDto = addressMapper.convertEntityResponseDto(userAddressSaved.getUserAddressId().getAddress());
        userAddressResponseDto.setAddressResponseDto(addressResponseDto);
        return userAddressResponseDto;
    }

    @Override
    public AddressByUserResponseDto getListAddressesByUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("UserId is invalid"));

        AddressByUserResponseDto addressByUserResponseDto = userMapper.convertEntityResponseDtoV2(user);

        List<UserAddressResponseDto> customerAddressResponseDtos = userAddressMapper.convertEntitiesResponseDtos(user.getUserAddresses());

        addressByUserResponseDto.setAddresses(customerAddressResponseDtos);

        return addressByUserResponseDto;
    }

    @Override
    public UserAddressResponseDto getAddressByUser(int userId, int addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Address doesn't exist"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User doesn't exist"));

        UserAddress userAddress = userAddressRepository.findById(new UserAddressId(user, address))
                .orElseThrow(() -> new IllegalArgumentException("User hasn't this address"));
        UserAddressResponseDto userAddressResponseDto =  userAddressMapper.convertEntityResponseDto(userAddress);
        AddressResponseDto addressResponseDto = addressMapper.convertEntityResponseDto(userAddress.getUserAddressId().getAddress());
        userAddressResponseDto.setAddressResponseDto(addressResponseDto);

        return userAddressResponseDto;

    }

    @Override
    @Transactional
    public UserAddressResponseDto updateAddressByUser(int userId, int addressId, UpdateAddressByUserRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("UserId is invalid"));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Address doesn't exist"));

        Optional<UserAddress> userAddress = userAddressRepository.findById(new UserAddressId(user, address));

        if (userAddress.isEmpty()) {
            throw new IllegalArgumentException("User doesn't have this address yet");
        }

        userAddress.get().setName(requestDto.getName());
        userAddress.get().setPhone(requestDto.getPhone());
        userAddress.get().setType(requestDto.isType());
        userAddress.get().setDefaultAddress(requestDto.isDefaultAddress());

        UserAddress userAddressSaved = userAddressRepository.save(userAddress.get());


        UserAddressResponseDto userAddressResponseDto = userAddressMapper.convertEntityResponseDto(userAddressSaved);
        AddressResponseDto addressResponseDto = addressMapper.convertEntityResponseDto(userAddressSaved.getUserAddressId().getAddress());
        userAddressResponseDto.setAddressResponseDto(addressResponseDto);

        return userAddressResponseDto;
    }

    @Override
    public void deleteAddressByUser(int userId, int addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Address doesn't exist"));

        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("User doesn't exist"));

        UserAddress userAddress = userAddressRepository.findById(new UserAddressId(user, address))
                        .orElseThrow(() -> new IllegalArgumentException("User hasn't this address"));


        userAddress.setDeleted(true);
        userAddressRepository.save(userAddress);
    }


}
