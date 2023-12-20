package com.datajpa;

import com.datajpa.entity.Goods;
import com.datajpa.entity.Producer;
import com.datajpa.repository.GoodsRepository;
import com.datajpa.repository.ProducerRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
//상품(Goods)와 제작업체(Producer)은 다대다(n:m) 연관 매핑 테스트
@SpringBootTest
@Slf4j
public class ManyToManyTest {

    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    GoodsRepository goodsRepository;

    @Test
    @Transactional
    void relationshipTest() {
        Goods goods1 = saveGoods("동글펜", 500, 1000);
        Goods goods2 = saveGoods("네모 공책", 100, 2000);
        Goods goods3 = saveGoods("지우개", 152, 1234);

        Producer producer1 = saveProducer("flature");
        Producer producer2 = saveProducer("wikibooks");

        producer1.addProducer(goods1);
        producer1.addProducer(goods2);
        producer2.addProducer(goods2);
        producer2.addProducer(goods3);

        producerRepository.saveAll(Lists.newArrayList(producer1, producer2));
        System.out.println(producerRepository.findById(1L).get().getGoods());
    }

    @Test
    @Transactional
    void relationshipTest2() {
        Goods goods1 = saveGoods("동글펜", 500, 1000);
        Goods goods2 = saveGoods("네모 공책", 100, 2000);
        Goods goods3 = saveGoods("지우개", 152, 1234);

        Producer producer1 = saveProducer("flature");
        Producer producer2 = saveProducer("wikibooks");

        producer1.addProducer(goods1);
        producer1.addProducer(goods2);
        producer2.addProducer(goods2);
        producer2.addProducer(goods3);

        goods1.addProducer(producer1);
        goods2.addProducer(producer1);
        goods2.addProducer(producer2);
        goods3.addProducer(producer2);

        producerRepository.saveAll(Lists.newArrayList(producer1, producer2));
        goodsRepository.saveAll(Lists.newArrayList(goods1, goods2, goods3));

        log.info("Goods Data : " + producerRepository.findById(1L).get().getGoods());
        log.info("Producers Data : " + goodsRepository.findById(2L).get().getProducers());
    }

    private Goods saveGoods(String name, Integer price, Integer amount) {
        Goods goods = new Goods();
        goods.setName(name);
        goods.setPrice(price);
        goods.setAmount(amount);
        log.info("Goods : " + goods.toString());
        return goodsRepository.save(goods);
    }

    private Producer saveProducer(String name) {
        Producer producer = new Producer();
        producer.setName(name);
        log.info("Producer : " + producer.toString());
        return producerRepository.save(producer);
    }

}
