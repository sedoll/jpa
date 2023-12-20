package com.test1.service;

import com.test1.dto.ProductDto;
import com.test1.entity.Product;
import com.test1.repo.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductDto> productList() {
        List<Product> lst = productRepo.findAll();
        List<ProductDto> dtoList = lst.stream().map(product ->
                modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public ProductDto getProduct(Integer no) {
        Optional<Product> pro = productRepo.findById(no);
        ProductDto dto = modelMapper.map(pro, ProductDto.class);
        return dto;
    }

    @Override
    public void proInsert(ProductDto dto) {
        Product pro = modelMapper.map(dto, Product.class);
        productRepo.save(pro);
    }

    @Override
    public void proUpdate(ProductDto dto) {
        Optional<Product> pro = productRepo.findById(dto.getNo());
        Product res = pro.orElseThrow();
        res.change(dto);
        productRepo.save(res);
    }

    @Override
    public void proDelete(Integer no) {
        productRepo.deleteById(no);
    }
}
