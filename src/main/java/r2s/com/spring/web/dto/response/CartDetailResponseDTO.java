package r2s.com.spring.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailResponseDTO implements Serializable {
    private int idCart;

    private int productId;

    private String name;

    private int quantity;

    private int cost;

    private String status;
}
