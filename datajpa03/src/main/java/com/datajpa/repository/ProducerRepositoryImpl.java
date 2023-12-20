package com.datajpa.repository;

import com.datajpa.entity.Producer;
import com.datajpa.entity.QProducer;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAInsertClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Slf4j
public class ProducerRepositoryImpl implements ProducerRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Producer> list() {
        JPAQuery<Producer> query = new JPAQuery(entityManager);
        QProducer qProducer = QProducer.producer;
        List<Producer> producerList = query
                .from(qProducer)
                .orderBy(qProducer.id.desc())
                .fetch();
        return producerList;
    }

    @Override
    public Producer get(Long id) {
        JPAQuery<Producer> query = new JPAQuery(entityManager);
        QProducer qProducer = QProducer.producer;
        Producer pro = query
                .from(qProducer)
                .where(qProducer.id.eq(id))
                .fetchOne();
        return pro;
    }

    @Transactional
    @Override
    public void insert(Producer pro) {
        QProducer q = QProducer.producer;
        JPAInsertClause ins = new JPAInsertClause(entityManager, q);
        ins.columns(q.code, q.name, q.email)
                .values(pro.getCode(), pro.getName(), pro.getEmail())
                .execute();
    }

    @Override
    public void update(Producer pro) {
        QProducer q = QProducer.producer;
        JPAUpdateClause upd = new JPAUpdateClause(entityManager, q);
        upd.set(q.code, pro.getCode())
                .set(q.name, pro.getName())
                .set(q.email, pro.getEmail())
                .where(q.id.eq(pro.getId()))
                .execute();
    }

    @Override
    public void delete(Long id) {
        QProducer q = QProducer.producer;
        JPADeleteClause del = new JPADeleteClause(entityManager, q);
        del.where(q.id.eq(id))
                .execute();
    }
}
