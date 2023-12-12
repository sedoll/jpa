package com.datajpa.service;

import com.datajpa.dto.ProductDto;
import com.datajpa.entity.Product;
import com.datajpa.repository.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductDto> productList() {
        List<Product> lst = productMapper.productList1();
        List<ProductDto> proList = lst.stream().map(product
                -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        return proList;
    }

    @Override
    public ProductDto getProduct(Long number) {
        Product product = productMapper.getProduct1(number);
        ProductDto res = modelMapper.map(product, ProductDto.class);
        return res;
    }

    @Override
    public void saveProduct(ProductDto productDto) {        //Product ModelMapper를 활용한 제품등록
        Product pro = modelMapper.map(productDto, Product.class);
        productMapper.insertProduct(pro);
    }

    @Override
    public void createProduct(ProductDto productDto) {  //Product builder를 활용한 제품등록
        Product pro = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .stock(productDto.getStock())
                .build();
        productMapper.insertProduct(pro);
    }

    @Override
    public void generateProduct(ProductDto productDto) {    //Product Entity를 활용한 제품등록
        Product pro = new Product();
        pro.generateProduct(productDto);
    }

    @Override
    public void changeProduct(ProductDto productDto) throws Exception {
        Product pro = productMapper.getProduct1(productDto.getNumber());
        pro.change(productDto.getName(), productDto.getPrice(), productDto.getStock());
        productMapper.updateProduct(pro);
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productMapper.deleteProduct(number);
    }

}
