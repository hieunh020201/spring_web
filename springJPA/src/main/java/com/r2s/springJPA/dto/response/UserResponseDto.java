package com.r2s.springJPA.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class UserResponseDto implements Serializable {
    private String name;

    private String username;

    private String email;

    private String phone;

    private Date birthday;

    private String gender;
}
