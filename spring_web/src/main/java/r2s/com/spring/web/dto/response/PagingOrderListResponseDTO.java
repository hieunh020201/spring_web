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
public class PagingOrderListResponseDTO implements Serializable {
    private List<OrderResponseDTO> orderList;

    private Integer size;

    private Integer page;

    private String sort;
}
