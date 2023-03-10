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
public class UpdateUserRequestDTO implements Serializable {
    private String name;

    private String username;

    private String phone;

    private String gender;

    private String email;

    private Date birthday;
}
