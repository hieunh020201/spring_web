package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.APIRequestDto;
import com.r2s.springJPA.dto.response.APIResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.entity.API;
import com.r2s.springJPA.util.Constants;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;


public interface APIService {
    public boolean checkAPI(String path, String method, Collection<? extends GrantedAuthority> authorities);

    PageResponseDto getAllAPIs(Pageable pageable);

    APIResponseDto insertAPI(APIRequestDto requestDto);

    APIResponseDto updateAPIById(int id, APIRequestDto requestDto);

    APIResponseDto getAPIById(int id);

    void deleteAPIById(int id);
}
