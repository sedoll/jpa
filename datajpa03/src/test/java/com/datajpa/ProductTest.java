package com.datajpa;

import com.datajpa.entity.Product;
import lombok.extern.slf4j.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class ProductTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void selectListTest(){   //제품 전체 목록 테스트
        List<Product> proList = productRepository.findAll();
        for(Product pro:proList){
            log.info(pro.toString());
        }
    }

    @Test
    public void selectTest(){       //제품 검색 테스트
        Long number = 1L;
        Optional<Product> res = productRepository.findById(number);
        log.info(res.toString());
    }


    @Test
    public void insertTest(){       //제품 등록 테스트
        Product pro = Product.builder()
                .name("제품1")
                .price(20000)
                .stock(10)
                .build();
        Product product = productRepository.save(pro);
        log.info(product.toString());
    }

    @Test
    public void insertDummyTest(){  //10개 더미 제품 등록 테스트
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Product pro = Product.builder()
                    .name("제품"+i)
                    .price(i*10000)
                    .stock(i*5+8)
                    .build();
            productRepository.save(pro);
        });
    }

    @Test
    public void updateTest(){   //제품 정보 수정 테스트
        Product pro = Product.builder()
                .number(1L)
                .name("기본 제품1")
                .price(29000)
                .stock(5)
                .build();
        Product product = productRepository.save(pro);
        log.info(product.toString());
    }

    @Test
    public void deleteTest(){
        Long number = 1L;
        productRepository.deleteById(number);
    }
}
