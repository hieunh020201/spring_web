package r2s.com.spring.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import r2s.com.spring.web.dto.request.CartDetailRequestDTO;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO implements Serializable {
    private int id;

    private int userId;

    private List<CartDetailRequestDTO> cartDetailRequestDTOList;
}
