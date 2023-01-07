package r2s.com.spring.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestDTO implements Serializable {
    private String username;

    private String name;

    private String password;

    private String phone;

    private String gender;

    private String email;

    private Date birthday;

    private String role;
}
