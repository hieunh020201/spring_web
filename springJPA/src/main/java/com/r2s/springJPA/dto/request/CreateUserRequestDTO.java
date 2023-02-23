package com.r2s.springJPA.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.sql.Date;

@Data
public class CreateUserRequestDTO implements Serializable {

    private String name;

    private String username;

    private String password;

    @Email
    private String email;

    private String phone;

    private Date birthday;

    private String gender;

    private String role;
}
