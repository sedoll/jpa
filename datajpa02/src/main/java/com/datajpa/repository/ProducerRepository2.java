package com.datajpa.repository;

import com.datajpa.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProducerRepository2 extends JpaRepository<Producer, Long> {
    @Query(value = "SELECT * FROM Producer WHERE name = ?1", nativeQuery = true)
    List<Producer> findByName(String name);  //위 ?숫자는 매개변수의 순번입니다.

    @Query(value = "SELECT * FROM Producer WHERE name = :name", nativeQuery = true)
    List<Producer> findByNameParam(@Param("name") String name);   //:의 뒤에 붙는 것은 @Param("매개변수명") 에서 나타낸 매개변수명입니다.

    @Query(value = "SELECT code, name, email FROM Producer WHERE name = :name", nativeQuery = true)
    List<Object[]> findByNameParam2(@Param("name") String name);    //:의 뒤에 붙는 것은 @Param("매개변수명") 에서 나타낸 매개변수명입니다.

    @Transactional
    @Modifying
    @Query(value = "update Producer set code = ?2, name = ?3, email = ?4 where id = ?1", nativeQuery = true)
    void updateAllById(Long id, String code, String name, String email);  //위 ?숫자는 매개변수의 순번입니다.

    @Transactional
    @Modifying
    @Query(value = "delete from Producer where id = ?1", nativeQuery = true)
    void deleteAllById(Long id);  //위 ?숫자는 매개변수의 순번입니다.

    @Transactional
    @Modifying
    @Query(value = "insert into producer(code, name, email) values (?1, ?2, ?3)", nativeQuery = true)
    void saveAllBy(String code, String name, String email);  //위 ?숫자는 매개변수의 순번입니다.
}
