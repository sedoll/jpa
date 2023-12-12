package com.datajpa.service;

import com.datajpa.dto.ProductDto;
import com.datajpa.entity.Product;
import com.datajpa.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductDto> productList() {
        List<Product> lst = productRepository.findAll();
        List<ProductDto> proList = lst.stream().map(product
                        -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        return proList;
    }

    @Override
    public ProductDto getProduct(Long number) {
        Optional<Product> product = productRepository.findById(number);
        ProductDto res = modelMapper.map(product, ProductDto.class);
        return res;
    }

    @Override
    public ProductDto findByNumber(Long number) {
        Optional<Product> product = productRepository.findById(number);
        ProductDto res = modelMapper.map(product, ProductDto.class);
        return res;
    }

    @Override
    public boolean existsByNumber(Long number) {
        Optional<Product> product = productRepository.findByNumber(number);
        boolean pw = false;
        if(product!=null){
            pw = true;
        }
        return pw;
    }

    @Override
    public List<ProductDto> findByName(String name) {
        List<Product> pList = productRepository.findByName2(name);
        List<ProductDto> proList = pList.stream().map(product
                        -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        return proList;
    }

    @Override
    public List<ProductDto> findAllByName(String name) {
        List<Product> pList = productRepository.findByName2(name);
        List<ProductDto> proList = pList.stream().map(product
                        -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        return proList;
    }

    @Override
    public void saveProduct(ProductDto productDto) {        //Product ModelMapper를 활용한 제품등록
        Product pro = modelMapper.map(productDto, Product.class);
        productRepository.save(pro);
    }

    @Override
    public void createProduct(ProductDto productDto) {  //Product builder를 활용한 제품등록
        Product pro = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .stock(productDto.getStock())
                .build();
        Product result = productRepository.save(pro);
        log.info(result.toString());
    }

    @Override
    public void generateProduct(ProductDto productDto) {    //Product Entity를 활용한 제품등록
        Product pro = new Product();
        pro.generateProduct(productDto);
    }

    @Override
    public void changeProduct(ProductDto productDto) throws Exception {
        Optional<Product> product = productRepository.findById(productDto.getNumber());
        Product pro = product.orElseThrow();
        pro.change(productDto.getName(), productDto.getPrice(), productDto.getStock());
        productRepository.save(pro);
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productRepository.deleteById(number);
    }

}
