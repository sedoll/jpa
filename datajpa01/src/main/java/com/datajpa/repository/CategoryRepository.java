package com.datajpa.repository;

import com.datajpa.entity.Category;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

//    EntityManagerFactory factory = Persistence.createEntityManagerFactory("category");
//    EntityManager entityManager = factory.createEntityManager();
//    EntityTransaction tx = entityManager.getTransaction();

    @PersistenceContext
    private EntityManager entityManager;

    public List<Category> findAll() {
        return entityManager.createQuery("select c from Category c", Category.class).getResultList();
    }

    public Category findOne(Long id){
        return entityManager.find(Category.class, id);
    }

    public void save(Category cate) {
        entityManager.persist(cate);
    }

    public void modify(Category cate) {
        entityManager.merge(cate);
    }

    public void remove(Category cate) {
        entityManager.remove(cate);
    }

}