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
public class OrderResponseDTO implements Serializable {
    private int id;

    private int cartId;

    private int totalCost;

    private String status;
}
