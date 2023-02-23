package com.r2s.springJPA.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class UpdateUserRequestDto implements Serializable {
    private String name;

    private String phone;

    private String email;

    private Date birthday;

    private String gender;

}
