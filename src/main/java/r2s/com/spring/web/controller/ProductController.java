package r2s.com.spring.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.spring.web.dto.request.CreateProductRequestDTO;
import r2s.com.spring.web.dto.response.CategoryResponseDTO;
import r2s.com.spring.web.dto.response.ProductListResponseDTO;
import r2s.com.spring.web.dto.response.ProductResponseDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @PostMapping
    public ResponseEntity insertProduct(@RequestBody CreateProductRequestDTO productRequestDTO) {
        ProductResponseDTO response = new ProductResponseDTO();
        response.setId(1);
        response.setName(productRequestDTO.getName());
        response.setPrice(productRequestDTO.getPrice());
        response.setUserId(productRequestDTO.getUserId());
        response.setCategoryId(productRequestDTO.getCategoryId());
        response.setInventoryNumber(productRequestDTO.getInventoryNumber());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllProduct() {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(1);
        productResponseDTO.setName("Ao len");
        productResponseDTO.setPrice(100000);
        productResponseDTO.setCategoryId(1);
        productResponseDTO.setUserId(1);
        productResponseDTO.setInventoryNumber(10);

        ProductResponseDTO productResponseDTO1 = new ProductResponseDTO();
        productResponseDTO1.setId(1);
        productResponseDTO1.setName("Quan Dui");
        productResponseDTO1.setPrice(120000);
        productResponseDTO1.setCategoryId(3);
        productResponseDTO1.setUserId(1);
        productResponseDTO1.setInventoryNumber(10);

        List<ProductResponseDTO> productList = new ArrayList<>();
        productList.add(productResponseDTO);
        productList.add(productResponseDTO1);

        ProductListResponseDTO response = new ProductListResponseDTO();
        response.setProductResponseDTOList(productList);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
