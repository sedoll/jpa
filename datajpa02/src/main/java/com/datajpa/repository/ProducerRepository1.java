package com.datajpa.repository;

import com.datajpa.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProducerRepository1 extends JpaRepository<Producer, Long> {
    @Query("SELECT p FROM Producer p WHERE p.name = ?1")
    List<Producer> findByName(String name);     //위 ?숫자는 매개변수의 순번입니다.

    @Query("SELECT p FROM Producer p WHERE p.name = :name")
    List<Producer> findByNameParam(@Param("name") String name);     //:의 뒤에 붙는 것은 @Param("매개변수명") 에서 나타낸 매개변수명입니다.

    @Query("SELECT p.code, p.name, p.email FROM Producer p WHERE p.name = :name")
    List<Object[]> findByNameParam2(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("update Producer p set p.code = ?2, p.name = ?3, p.email = ?4 where p.id = ?1")
    void updateAllById(Long id, String code, String name, String email);    //위 ?숫자는 매개변수의 순번입니다.

    @Transactional
    @Modifying
    @Query("delete from Producer p where p.id = ?1")
    void deleteAllById(Long id);
}
