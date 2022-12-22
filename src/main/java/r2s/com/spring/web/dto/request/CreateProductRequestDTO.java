package r2s.com.spring.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequestDTO {
    private String name;

    private int categoryId;

    private int price;

    private int userId;

    private int inventoryNumber;
}
