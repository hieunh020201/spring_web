package r2s.com.spring.web.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagingAddressListResponseDTO implements Serializable {

    @JsonProperty(value = "data_list")
    private List<AddressResponseDTO> addressList;

    private Integer size;

    private Integer page;

    private String sort;
}
