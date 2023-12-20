package com.datajpa;

import com.datajpa.entity.Category;
import com.datajpa.entity.Goods;
import com.datajpa.repository.CategoryRepository;
import com.datajpa.repository.GoodsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
//카테고리(Category)는 상품(Goods)와 1:n 연관 매핑 테스트
@SpringBootTest
@Slf4j
public class OneToManyTest {
    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void relationshipTest(){
        // 테스트 데이터 생성
        Goods goods = new Goods();
        goods.setName("펜");
        goods.setPrice(2000);
        goods.setAmount(100);
        goodsRepository.save(goods);

        Category category = new Category();
        category.setCode("S1");
        category.setName("도서");
        category.getGoodsList().add(goods);
        categoryRepository.save(category);

        // 테스트 코드
        List<Goods> goodss = categoryRepository.findById(1L).get().getGoodsList();
        for(Goods foundProduct : goodss){
            log.info(foundProduct.toString());
        }
    }
}
