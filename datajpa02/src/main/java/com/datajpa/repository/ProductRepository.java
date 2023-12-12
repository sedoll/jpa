package com.datajpa.repository;

import com.datajpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /* 1. JPQL 사용하기 : 정해진 규칙에 의하여 메소드이름과 매개변수 그리고 반환타입을 지정하면, 해당 SQL로 변환한다. */
    // find..By
    Optional<Product> findByNumber(Long number);
    List<Product> findAllByName(String name);

    // exists..By
    boolean existsByNumber(Long number);

    // count..By
    long countByName(String name);

    // delete..By, remove..By
    void deleteByNumber(Long number);
    long removeByName(String name);

    // …First<number>…, …Top<number>…
    List<Product> findFirst5ByName(String name);
    List<Product> findTop10ByName(String name);

    /* 쿼리 메소드의 조건자 키워드 */
    Product findByNumberIs(Long number);   // Is, Equals => Logical Keyword : IS , Keyword Expressions : Is, Equals, (or no keyword) => findByNumber 메소드와 동일하게 동작
    Product findByNumberEquals(Long number);

    Product findByNumberIsNot(Long number);         // (Is)Not
    Product findByNumberNot(Long number);

    List<Product> findByUpdatedAtNull();        // (Is)Null, (Is)NotNull
    List<Product> findByUpdatedAtIsNull();
    List<Product> findByUpdatedAtNotNull();
    List<Product> findByUpdatedAtIsNotNull();

    Product findByNumberAndName(Long number, String name);          // And, Or
    Product findByNumberOrName(Long number, String name);

    List<Product> findByPriceIsGreaterThan(Long price);             // (Is)GreaterThan, (Is)LessThan, (Is)Between
    List<Product> findByPriceGreaterThan(Long price);
    List<Product> findByPriceGreaterThanEqual(Long price);
    List<Product> findByPriceIsLessThan(Long price);
    List<Product> findByPriceLessThan(Long price);
    List<Product> findByPriceLessThanEqual(Long price);
    List<Product> findByPriceIsBetween(Long lowPrice, Long highPrice);
    List<Product> findByPriceBetween(Long lowPrice, Long highPrice);

    List<Product> findByNameLike(String name);      // (Is)Like, (Is)Containing, (Is)StartingWith, (Is)EndingWith
    List<Product> findByNameIsLike(String name);

    List<Product> findByNameContains(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameIsContaining(String name);

    List<Product> findByNameStartsWith(String name);
    List<Product> findByNameStartingWith(String name);
    List<Product> findByNameIsStartingWith(String name);

    List<Product> findByNameEndsWith(String name);
    List<Product> findByNameEndingWith(String name);
    List<Product> findByNameIsEndingWith(String name);

    /* 정렬 처리하기 */
    List<Product> findByNameOrderByNumberAsc(String name);          // Asc : 오름차순, Desc : 내림차순
    List<Product> findByNameOrderByNumberDesc(String name);

    List<Product> findByNameOrderByPriceAscStockDesc(String name);      // 여러 정렬 기준 사용하기, And를 붙이지 않음

    List<Product> findByName(String name, Sort sort);       // 매개변수를 활용한 정렬 방법

    /* 2. Query 어노테이션 사용하기 */
    @Query("SELECT p FROM Product p WHERE p.name = ?1")
    List<Product> findByName2(String name);

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    List<Product> findByNameParam(@Param("name") String name);

    @Query("SELECT p.name, p.price, p.stock FROM Product p WHERE p.name = :name")
    List<Object[]> findByNameParam2(@Param("name") String name);
}
