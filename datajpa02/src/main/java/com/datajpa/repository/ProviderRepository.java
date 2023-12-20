package com.datajpa.repository;

import com.datajpa.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    @Query("SELECT p FROM Provider p WHERE p.name = ?1")
    List<Provider> findByName(String name);

    @Query("SELECT p FROM Provider p WHERE p.name = :name")
    List<Provider> findByNameParam(@Param("name") String name);

    @Query("SELECT p.name, p.tel, p.addr FROM Provider p WHERE p.name = :name")
    List<Object[]> findByNameParam2(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("update Provider p set p.name = ?2, p.tel = ?3, p.addr = ?4 where p.id = ?1")
    void updateAllById(Long id, String name, String tel, String addr);

    @Transactional
    @Modifying
    @Query("delete from Provider p where p.id = ?1")
    void deleteAllById(Long id);
}
