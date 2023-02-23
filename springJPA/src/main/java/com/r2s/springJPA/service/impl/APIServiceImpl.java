package com.r2s.springJPA.service.impl;

import com.r2s.springJPA.dto.request.APIRequestDto;
import com.r2s.springJPA.dto.response.APIResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.entity.API;
import com.r2s.springJPA.entity.Role;
import com.r2s.springJPA.mapper.APIMapper;
import com.r2s.springJPA.repository.APIRepository;
import com.r2s.springJPA.repository.RoleRepository;
import com.r2s.springJPA.service.APIService;
import com.r2s.springJPA.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class APIServiceImpl implements APIService {

    @Autowired
    private APIRepository apiRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private APIMapper apiMapper;

    @Transactional
    @Override
    public boolean checkAPI(String path, String method, Collection<? extends GrantedAuthority> authorities) {
        boolean flag = false;
        for (API api: apiRepository.findAll()) {
            Pattern pattern =Pattern.compile(api.getPath());
            if (pattern.matcher(path).matches()) {
                if (method.equals(api.getMethod())) {
                    if (authorities.containsAll(api.getRoles())) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    @Override
    public PageResponseDto getAllAPIs(Pageable pageable) {
        Page<API> apisPage = apiRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Can't get api by paging"));

        PageResponseDto pageResponseDto = new PageResponseDto();
        pageResponseDto.setPage(apisPage.getNumber());
        pageResponseDto.setSize(apisPage.getSize());
        pageResponseDto.setTotalPages(apisPage.getTotalPages());
        pageResponseDto.setTotalRecord(apisPage.getTotalElements());
        List<APIResponseDto> responseDtos = apiMapper.convertEntitiesResponseDtos(apisPage.getContent());
        pageResponseDto.setData(responseDtos);
        return pageResponseDto;
    }

    @Override
    public APIResponseDto insertAPI(APIRequestDto requestDto) {
        Set<Role> roles = new HashSet<>();
        requestDto.getRoles().stream().forEach(item -> {
            Role role = roleRepository.findByAuthority(item)
                    .orElseThrow(()-> new IllegalArgumentException("Role doesn't exist"));
            roles.add(role);
        });
        API api = new API();
        api.setPath(requestDto.getPath());
        api.setMethod(requestDto.getMethod());
        api.setRoles(roles);

        return apiMapper.convertEntityResponseDto(apiRepository.save(api));
    }

    @Override
    public APIResponseDto updateAPIById(int id, APIRequestDto requestDto) {
        API api = apiRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("API doesn't exist"));

        api.setMethod(requestDto.getMethod());
        api.setPath(requestDto.getPath());

        return apiMapper.convertEntityResponseDto(apiRepository.save(api));
    }

    @Override
    public APIResponseDto getAPIById(int id) {
        API api = apiRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("API doesn't exist"));

        return apiMapper.convertEntityResponseDto(apiRepository.save(api));
    }

    @Override
    public void deleteAPIById(int id) {
        API api = apiRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("API doesn't exist"));

        apiMapper.convertEntityResponseDto(apiRepository.save(api));
    }
}
