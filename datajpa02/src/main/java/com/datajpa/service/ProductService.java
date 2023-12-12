package com.datajpa.service;

import com.datajpa.dto.*;
import com.datajpa.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> productList();
    ProductDto getProduct(Long number);
    ProductDto findByNumber(Long number);
    boolean existsByNumber(Long number);
    List<ProductDto> findByName(String name);
    List<ProductDto> findAllByName(String name);
    void saveProduct(ProductDto productDto);
    void changeProduct(ProductDto productDto) throws Exception;
    void deleteProduct(Long number) throws Exception;
    void createProduct(ProductDto productDto);
    void generateProduct(ProductDto productDto);
}
