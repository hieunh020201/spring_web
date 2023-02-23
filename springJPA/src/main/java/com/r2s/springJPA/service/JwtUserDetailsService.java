package com.r2s.springJPA.service;

import com.r2s.springJPA.entity.Role;
import com.r2s.springJPA.entity.User;
import com.r2s.springJPA.repository.RoleRepository;
import com.r2s.springJPA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        } else {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            for (Role role: user.get().getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
            }
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),
                    authorities);
        }
    }
}
