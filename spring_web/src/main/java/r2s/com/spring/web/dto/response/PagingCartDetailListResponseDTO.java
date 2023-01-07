package r2s.com.spring.web.dto.response;

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
public class PagingCartDetailListResponseDTO implements Serializable {

    private List<CartDetailResponseDTO> cartDetailList;

    private Integer size;

    private Integer page;

    private String sort;
}
