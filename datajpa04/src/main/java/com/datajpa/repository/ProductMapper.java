package com.datajpa.repository;

import com.datajpa.entity.Product;

import java.util.List;

public interface ProductMapper {
    List<Product> productList1();
    List<Product> productList2();
    List<String> productList3();
    Product getProduct1(Long number);
    Product getProduct2(Long number);
    void insertProduct(Product pro);
    void updateProduct(Product pro);
    void deleteProduct(Long number);
}
