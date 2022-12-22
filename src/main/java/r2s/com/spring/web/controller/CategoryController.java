package r2s.com.spring.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.spring.web.dto.response.CategoryResponseDTO;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @PostMapping
    public ResponseEntity insertCategory(@RequestBody String categoryName) {
        String name = categoryName;
        StringBuilder stringBuilder = new StringBuilder();
        String response = stringBuilder.append("Category has name: ").append(name).toString();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllCategory() {
        CategoryResponseDTO categoryResponseDTO1 = new CategoryResponseDTO();
        categoryResponseDTO1.setId(1);
        categoryResponseDTO1.setName("Quan Ao");

        CategoryResponseDTO categoryResponseDTO2 = new CategoryResponseDTO();
        categoryResponseDTO2.setId(2);
        categoryResponseDTO2.setName("Dong Ho");

        List<CategoryResponseDTO> response = new ArrayList<>();
        response.add(categoryResponseDTO1);
        response.add(categoryResponseDTO2);

        return new ResponseEntity(response, HttpStatus.OK);
    }

}
