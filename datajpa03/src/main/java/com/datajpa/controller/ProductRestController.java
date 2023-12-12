package com.datajpa.controller;

import com.datajpa.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.datajpa.service.ProductService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    @GetMapping("/getPro")
    public ResponseEntity<ProductDto> getProduct(Long number){
        ProductDto productDto = productService.getProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @PutMapping("/modify")
    public ResponseEntity<ProductDto> changeProductName(
            @RequestBody ProductDto productDto) throws Exception {
                productService.changeProduct(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}
