package com.r2s.springJPA.controller;

import com.r2s.springJPA.dto.request.APIRequestDto;
import com.r2s.springJPA.dto.request.CreateOrderRequestDTO;
import com.r2s.springJPA.dto.response.APIResponseDto;
import com.r2s.springJPA.dto.response.OrderResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.service.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class APIController {
    @Autowired
    private APIService apiService;

    @GetMapping("/apis")
    public ResponseEntity<?> getALlAPIs(Pageable pageable) {
        PageResponseDto pageResponseDto = apiService.getAllAPIs(pageable);
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @PostMapping("/api")
    public ResponseEntity<?> insertAPI(@RequestBody APIRequestDto requestDTO) {
        APIResponseDto apiResponseDto = apiService.insertAPI(requestDTO);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @PutMapping("/api/{api-id}")
    public ResponseEntity<?> updateAPIById(@PathVariable(value = "api-id") int apiId, @RequestBody APIRequestDto requestDTO) {
        APIResponseDto apiResponseDto = apiService.updateAPIById(apiId, requestDTO);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
