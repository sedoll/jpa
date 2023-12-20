package com.datajpa;

import com.datajpa.entity.Product;
import com.datajpa.entity.ProductDetail;
import com.datajpa.repository.ProductDetailRepository;
import com.datajpa.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//제품(Product)와 제품상세(ProductDetail)은 1:1 연관 매핑 테스팅
@SpringBootTest
@Slf4j
public class OneToOneTest {
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void saveAndReadTest1() {
        Product product = new Product();
        product.setName("스프링 부트 DATA JPA");
        product.setPrice(5000);
        product.setStock(500);

        productRepository.save(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setDescription("스프링 부트 시리즈의 고급과정 교재");

        productDetailRepository.save(productDetail);

        // 생성한 데이터 조회
        log.info("savedProduct : " + productDetailRepository.findById(productDetail.getId()).get().getProduct());
        log.info("savedProductDetail : " + productDetailRepository.findById(productDetail.getId()).get());
    }

}
