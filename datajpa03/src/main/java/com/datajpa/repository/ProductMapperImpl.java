package com.datajpa.repository;

import com.datajpa.entity.Product;
import com.datajpa.entity.QProduct;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class ProductMapperImpl implements ProductMapper {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Product> productList1(){
        JPAQuery<Product> query = new JPAQuery(entityManager);
        QProduct qProduct = QProduct.product;
        List<Product> productList = query
                .from(qProduct)
                .orderBy(qProduct.number.desc())
                .fetch();
        return productList;
    }

    @Override
    public List<Product> productList2(){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;
        List<Product> productList = jpaQueryFactory.selectFrom(qProduct)
                .orderBy(qProduct.number.desc())
                .fetch();
        return productList;
    }

    @Override
    public List<String> productList3(){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;
        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();
        return productList;
    }

    @Override
    public Product getProduct1(Long number){
        JPAQuery<Product> query = new JPAQuery(entityManager);
        QProduct qProduct = QProduct.product;
        Product pro = query
                .from(qProduct)
                .where(qProduct.number.eq(number))
                .fetchOne();
        return pro;
    }

    @Override
    public Product getProduct2(Long number){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;
        Product pro = jpaQueryFactory
                .selectFrom(qProduct)
                .where(qProduct.number.eq(number))
                .orderBy(qProduct.price.asc())
                .fetchOne();
        return pro;
    }

    @Override
    public void insertProduct(Product pro){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        Date now = new Date();
        QProduct qPro = QProduct.product;
        jpaQueryFactory.insert(qPro)
                .columns(qPro.name, qPro.price, qPro.stock, qPro.createdAt)
                .values(pro.getName(), pro.getPrice(), pro.getStock(), now)
                .execute();
    }

    @Override
    public void updateProduct(Product pro){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        LocalDateTime now = LocalDateTime.now();
        QProduct qPro = QProduct.product;
        jpaQueryFactory.update(qPro)
                .where(qPro.number.eq(pro.getNumber()))
                .set(qPro.name, pro.getName())
                .set(qPro.price, pro.getPrice())
                .set(qPro.stock, pro.getStock())
                .set(qPro.updatedAt, now)
                .execute();
    }

    @Override
    public void deleteProduct(Long number){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qPro = QProduct.product;
        jpaQueryFactory.delete(qPro)
                .where(qPro.number.eq(number))
                .execute();
    }
}