package com.datajpa.repository;

import com.datajpa.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository {
    List<Goods> list();
    Goods get(Long gno);
    List<Goods> findAllByName(String name);
    List<Goods> findAllByNameContains(String name);
    void insert(Goods goods);
    void update(Goods goods);
    void delete(Long gno);
}
