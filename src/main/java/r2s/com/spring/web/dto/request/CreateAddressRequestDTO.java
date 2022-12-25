package r2s.com.spring.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressRequestDTO implements Serializable {
    private String name;

    private String phone;

    private String province;

    private String district;

    private String street;

    private boolean type;

    private boolean defaultAddress;

    private int userId;
}
