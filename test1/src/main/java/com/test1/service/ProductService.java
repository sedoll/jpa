package com.test1.service;

import com.test1.dto.ProductDto;
import com.test1.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductDto> productList();
    ProductDto getProduct(Integer no);
    void proInsert(ProductDto dto);
    void proUpdate(ProductDto dto);
    void proDelete(Integer no);
}
