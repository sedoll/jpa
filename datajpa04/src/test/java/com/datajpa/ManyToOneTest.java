package com.datajpa;

import com.datajpa.entity.Goods;
import com.datajpa.entity.Provider;
import com.datajpa.repository.GoodsRepository;
import com.datajpa.repository.ProductRepository;
import com.datajpa.repository.ProviderRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.transaction.Transactional;
import java.util.List;

//상품(Goods)은 제공업체(Provider)와 다대일(n:1) 연관 매핑 테스트
@SpringBootTest
@Slf4j
public class ManyToOneTest {
    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Test
    void relationshipTest1() {
        // 테스트 데이터 생성
        Provider provider = new Provider();
        provider.setName("ㅇㅇ물산");
        provider.setAddr("구로2동");
        provider.setTel("02-1234-1234");
        providerRepository.save(provider);

        Goods goods = new Goods();
        goods.setName("해법영어");
        goods.setPrice(5000);
        goods.setAmount(500);
        goods.setProvider(provider);
        goodsRepository.save(goods);

        // 테스트
        log.info("product : " + goodsRepository.findById(1L).orElseThrow(RuntimeException::new));
        log.info("provider : " + goodsRepository.findById(1L).orElseThrow(RuntimeException::new).getProvider());
    }

    @Test
    void relationshipTest2() {
        // 테스트 데이터 생성
        Provider provider = new Provider();
        provider.setName("ㅇㅇ상사");

        providerRepository.save(provider);

        Goods goods1 = new Goods();
        goods1.setName("펜");
        goods1.setPrice(2000);
        goods1.setAmount(100);
        goods1.setProvider(provider);

        Goods goods2 = new Goods();
        goods2.setName("가방");
        goods2.setPrice(20000);
        goods2.setAmount(200);
        goods2.setProvider(provider);

        Goods goods3 = new Goods();
        goods3.setName("노트");
        goods3.setPrice(3000);
        goods3.setAmount(1000);
        goods3.setProvider(provider);

        goodsRepository.save(goods1);
        goodsRepository.save(goods2);
        goodsRepository.save(goods3);

        log.info("check 1");

        List<Goods> goodss = providerRepository.findById(provider.getId()).get().getGoodsList();
        for (Goods pro : goodss) {
            log.info(pro.toString());
        }
    }

    @Test
    void cascadeTest() {
        Provider provider = savedProvider("새로운 공급업체");
        Goods goods1 = savedGoods("상품1", 1000, 1000);
        Goods goods2 = savedGoods("상품2", 500, 1500);
        Goods goods3 = savedGoods("상품3", 750, 500);
        // 연관관계 설정
        goods1.setProvider(provider);
        goods2.setProvider(provider);
        goods3.setProvider(provider);
        provider.getGoodsList().addAll(Lists.newArrayList(goods1, goods2, goods3));
        providerRepository.save(provider);
    }

    @Test
    @Transactional
    void orphanRemovalTest() {
        Provider provider = savedProvider("새로운 공급업체");

        Goods goods1 = savedGoods("상품1", 1000, 1000);
        Goods goods2 = savedGoods("상품2", 500, 1500);
        Goods goods3 = savedGoods("상품3", 750, 500);

        // 연관관계 설정
        goods1.setProvider(provider);
        goods2.setProvider(provider);
        goods3.setProvider(provider);

        provider.getGoodsList().addAll(Lists.newArrayList(goods1, goods2, goods3));

        providerRepository.saveAndFlush(provider);

        log.info("## Before Removal ##");
        log.info("## provider list ##");
        providerRepository.findAll().forEach(System.out::println);

        log.info("## product list ##");
        goodsRepository.findAll().forEach(System.out::println);

        // 연관관계 제거
        Provider foundProvider = providerRepository.findById(1L).get();
        foundProvider.getGoodsList().remove(0);

        log.info("## After Removal ##");
        log.info("## provider list ##");
        providerRepository.findAll().forEach(System.out::println);

        log.info("## product list ##");
        goodsRepository.findAll().forEach(System.out::println);
    }

    private Provider savedProvider(String name) {
        Provider provider = new Provider();
        provider.setName(name);
        return provider;
    }

    private Goods savedGoods(String name, Integer price, Integer stock) {
        Goods goods = new Goods();
        goods.setName(name);
        goods.setPrice(price);
        goods.setAmount(stock);
        return goods;
    }


}
