package com.datajpa.service;

import com.datajpa.dto.ProductDetailDto;


import java.util.List;

public interface ProductDetailService {
    List<ProductDetailDto> list();
    ProductDetailDto get(Long id);
    void save(ProductDetailDto dto);
    void change(ProductDetailDto dto);
    void delete(long id);
}
