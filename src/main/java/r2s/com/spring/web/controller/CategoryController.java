package r2s.com.spring.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.spring.web.dto.request.CategoryRequestDTO;
import r2s.com.spring.web.dto.response.CategoryResponseDTO;
import r2s.com.spring.web.dto.response.PagingCategoryListResponseDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @PostMapping
    public ResponseEntity insertCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        CategoryResponseDTO response = new CategoryResponseDTO();
        response.setId(1);
        response.setName(categoryRequestDTO.getName());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity getAllCategory(@RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "size", required = false) Integer size,
                                         @RequestParam(value = "sort", required = false) String type_sort) {
        CategoryResponseDTO categoryResponseDTO1 = new CategoryResponseDTO();
        categoryResponseDTO1.setId(1);
        categoryResponseDTO1.setName("Quan Ao");

        CategoryResponseDTO categoryResponseDTO2 = new CategoryResponseDTO();
        categoryResponseDTO2.setId(2);
        categoryResponseDTO2.setName("Dong Ho");

        PagingCategoryListResponseDTO response = new PagingCategoryListResponseDTO();
        List<CategoryResponseDTO> categoryList = new ArrayList<>();
        categoryList.add(categoryResponseDTO1);
        categoryList.add(categoryResponseDTO2);

        response.setCategoryList(categoryList);
        response.setPage(page);
        response.setSize(size);
        response.setSort(type_sort);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{category-id}")
    public ResponseEntity getDetailCategoryByCategoryId(@PathVariable(value = "category-id") int categoryId) {
        CategoryResponseDTO response = new CategoryResponseDTO();
        response.setId(categoryId);
        response.setName("Giay dep");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/{category-id}")
    public ResponseEntity updateCategory(@PathVariable(value = "category-id") int categoryId,
                                     @RequestBody CategoryRequestDTO categoryRequestDTO) {
        CategoryResponseDTO response = new CategoryResponseDTO();
        response.setId(categoryId);
        response.setName(categoryRequestDTO.getName());

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{category-id}")
    public ResponseEntity deleteCategory(@PathVariable(value = "category-id") int categoryId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
