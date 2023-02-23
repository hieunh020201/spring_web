package com.r2s.springJPA.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "API")
public class API {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "PATH")
    private String path;

    @ManyToMany
    @JoinTable(name = "ROLE_API",
            joinColumns = @JoinColumn(name = "API_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

    @Column(name = "METHOD")
    private String method;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;
}
