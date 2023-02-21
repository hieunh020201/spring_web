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
public class PagingProductListResponseDTO implements Serializable {

    private List<ProductResponseDTO> productList;

    private Integer size;

    private Integer page;

    private String sort;
}
