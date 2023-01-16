package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CreateCustomerRequestDTO;
import com.r2s.springJPA.dto.request.UpdateCustomerRequestDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.dto.response.CustomerResponseDto;
import com.r2s.springJPA.entity.Customer;
import com.r2s.springJPA.mapper.CustomerMapper;
import com.r2s.springJPA.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public PageResponseDto getAllCustomers(Pageable pageable) {
        Page<Customer> customersPage = customerRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Can't get Users by paging"));

        PageResponseDto pageResponseDto = new PageResponseDto();
        pageResponseDto.setPage(customersPage.getNumber());
        pageResponseDto.setSize(customersPage.getSize());
        pageResponseDto.setTotalPages(customersPage.getTotalPages());
        pageResponseDto.setTotalRecord(customersPage.getTotalElements());
        List<CustomerResponseDto> customerResponseDtos = customerMapper.convertEntitiesResponseDtos(customersPage.getContent());
        pageResponseDto.setData(customerResponseDtos);
        return pageResponseDto;
    }

    @Override
    public CustomerResponseDto insertCustomer(CreateCustomerRequestDTO requestDTO) {
        Customer user = new Customer();
        user.setName(requestDTO.getName());
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword());
        user.setPhone(requestDTO.getPhone());
        user.setEmail(requestDTO.getEmail());
        user.setGender(requestDTO.getGender());
        user.setBirthday(requestDTO.getBirthday());
        user.setRole(requestDTO.getRole());
        user.setDeleted(false);
        return customerMapper.convertEntityResponseDto(customerRepository.save(user));
    }

    @Transactional
    @Override
    public CustomerResponseDto updateCustomer(int userId, UpdateCustomerRequestDto requestDto) {
        if (!customerRepository.existsById(userId)) {
            throw new IllegalArgumentException("CustomerId is invalid");
        } else {
            Customer user = customerRepository.findById(userId).get();
            user.setName(requestDto.getName());
            user.setPhone(requestDto.getPhone());
            user.setEmail(requestDto.getEmail());
            user.setBirthday(requestDto.getBirthday());
            user.setGender(requestDto.getGender());
            return customerMapper.convertEntityResponseDto(customerRepository.save(user));
        }
    }

    @Override
    public void deleteCustomer(int userId) {
        if (!customerRepository.existsById(userId)) {
            throw new IllegalArgumentException("CustomerId is invalid");
        } else {
            Customer user = customerRepository.findById(userId).get();
            user.setDeleted(true);
            customerRepository.save(user);
        }
    }

    @Override
    public CustomerResponseDto getCustomerById(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("CustomerId is invalid"));
        return customerMapper.convertEntityResponseDto(customer);
    }
}
