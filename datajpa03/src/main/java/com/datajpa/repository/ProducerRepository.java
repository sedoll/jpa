package com.datajpa.repository;

import com.datajpa.entity.Producer;
import java.util.List;

public interface ProducerRepository {
    List<Producer> list();
    Producer get(Long id);
    void insert(Producer pro);
    void update(Producer pro);
    void delete(Long gno);
}
