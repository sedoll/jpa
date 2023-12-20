package com.datajpa.repository;

import com.datajpa.entity.Goods;
import com.datajpa.entity.Product;
import com.datajpa.entity.QGoods;
import com.datajpa.entity.QProduct;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
@Slf4j
public class GoodsRepositoryImpl implements GoodsRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Goods> list() {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QGoods qGoods = QGoods.goods;
        List<Goods> goodsList = query
                .selectFrom(qGoods)
                .orderBy(qGoods.gno.desc())
                .fetch();
        return goodsList;
    }

    @Override
    public Goods get(Long gno) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QGoods qGoods = QGoods.goods;
        Goods goods = query
                .selectFrom(qGoods)
                .where(qGoods.gno.eq(gno))
                .orderBy(qGoods.gno.desc())
                .fetchOne();
        return goods;
    }

    @Override
    public List<Goods> findAllByName(String name) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QGoods qGoods = QGoods.goods;
        List<Goods> goodsList = query
                .selectFrom(qGoods)
                .where(qGoods.name.eq(name))
                .orderBy(qGoods.gno.desc())
                .fetch();
        return goodsList;
    }

    @Override
    public List<Goods> findAllByNameContains(String name) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QGoods qGoods = QGoods.goods;
        List<Goods> goodsList = query
                .selectFrom(qGoods)
                .where(qGoods.name.contains(name))
                .orderBy(qGoods.gno.desc())
                .fetch();
        return goodsList;
    }

    @Override
    public void insert(Goods goods) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        LocalDateTime now = LocalDateTime.now();
        QGoods qGoods = QGoods.goods;
        jpaQueryFactory.insert(qGoods)
                .columns(qGoods.name, qGoods.price, qGoods.amount, qGoods.createdAt)
                .values(goods.getName(), goods.getPrice(), goods.getAmount(), now)
                .execute();
    }

    @Override
    public void update(Goods goods) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        LocalDateTime now = LocalDateTime.now();
        QGoods qGoods = QGoods.goods;
        jpaQueryFactory.update(qGoods)
                .where(qGoods.gno.eq(goods.getGno()))
                .set(qGoods.name, goods.getName())
                .set(qGoods.price, goods.getPrice())
                .set(qGoods.amount, goods.getAmount())
                .set(qGoods.updatedAt, now)
                .execute();
    }

    @Override
    public void delete(Long gno) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QGoods qGoods = QGoods.goods;
        jpaQueryFactory.delete(qGoods)
                .where(qGoods.gno.eq(gno))
                .execute();
    }
}
