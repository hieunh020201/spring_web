package r2s.com.spring.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequestDTO implements Serializable {
    private String name;

    private int categoryId;

    private int price;

    private int inventoryNumber;
}
