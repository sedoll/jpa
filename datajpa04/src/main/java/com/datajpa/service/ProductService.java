package com.datajpa.service;

import com.datajpa.dto.*;

import java.util.List;

public interface ProductService {
    List<ProductDto> productList();
    ProductDto getProduct(Long number);
    void saveProduct(ProductDto productDto);
    void changeProduct(ProductDto productDto) throws Exception;
    void deleteProduct(Long number) throws Exception;
    void createProduct(ProductDto productDto);
    void generateProduct(ProductDto productDto);
}
