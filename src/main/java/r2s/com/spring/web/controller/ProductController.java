package r2s.com.spring.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.spring.web.dto.request.CreateProductRequestDTO;
import r2s.com.spring.web.dto.request.UpdateProductRequestDTO;
import r2s.com.spring.web.dto.response.*;

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

    @GetMapping(value = "/get-all")
    public ResponseEntity getAllProduct(@RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "size", required = false) Integer size,
                                        @RequestParam(value = "sort", required = false) String type_sort) {
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

        PagingProductListResponseDTO response = new PagingProductListResponseDTO();
        response.setProductList(productList);
        response.setSize(size);
        response.setPage(page);
        response.setSort(type_sort);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity getDetailProductByProductId(@PathVariable(value = "product-id") int productId) {
        ProductResponseDTO response = new ProductResponseDTO();
        response.setId(productId);
        response.setName("Ao thun");
        response.setPrice(100000);
        response.setCategoryId(1);
        response.setUserId(1);
        response.setInventoryNumber(10);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/{product-id}")
    public ResponseEntity updateProduct(@PathVariable(value = "product-id") int productId,
                                     @RequestBody UpdateProductRequestDTO updateProductRequestDTO) {
        ProductResponseDTO response = new ProductResponseDTO();
        response.setId(productId);
        response.setName(updateProductRequestDTO.getName());
        response.setPrice(updateProductRequestDTO.getPrice());
        response.setCategoryId(updateProductRequestDTO.getCategoryId());
        response.setUserId(1);
        response.setInventoryNumber(updateProductRequestDTO.getInventoryNumber());

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity deleteProduct(@PathVariable(value = "product-id") int productId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
