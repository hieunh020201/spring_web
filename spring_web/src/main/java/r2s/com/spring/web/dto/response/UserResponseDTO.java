package r2s.com.spring.web.dto.response;

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
public class UserResponseDTO implements Serializable {

    private int id;

    private String username;

    private String name;

    private String phone;

    private String gender;

    private String email;

    private Date birthday;

    private String role;
}
