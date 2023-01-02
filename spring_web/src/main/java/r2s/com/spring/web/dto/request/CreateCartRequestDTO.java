package r2s.com.spring.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCartRequestDTO implements Serializable {
    private int userId;

    private Date createAt;

    @JsonProperty(value = "cartDetailList")
    private List<CartDetailRequestDTO> cartDetailRequestDTOList;

}
