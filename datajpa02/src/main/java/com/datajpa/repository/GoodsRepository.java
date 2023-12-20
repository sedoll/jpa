package com.datajpa.repository;

import com.datajpa.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
    Optional<Goods> findByGno(Long gno);  //해당 테이블에서 기본키인 Gno를 기준으로 매개변수로 입력된 gno 해당하는 엔티티를 반환합니다.
    List<Goods> findAllByName(String name); //해당 테이블에서 입력 되어진 name 값이 일치하는 Goods의 ResultSet 을 목록으로 반환합니다.
    boolean existsByGno(Long gno);    //해당 테이블에서 매개변수로 입력된 gno에 해당하는 엔티티가 존재하는지 여부를 반환합니다.
    long countByName(String name);  //해당 테이블에서 매개변수로 입력된 name에 해당하는 엔티티의 개수를 반환합니다.
    void deleteByGno(Long gno);   //해당 테이블에서 매개변수로 입력된 gno에 해당하는 엔티티를 제거합니다.
    long removeByName(String name); //해당 테이블에서 매개변수로 입력된 name에 해당하는 엔티티를 제거하고, 제거된 엔티티의 수를 반환합니다.
    List<Goods> findFirst5ByName(String name);  //해당 테이블에서 매개변수로 입력된 name에 해당하는 엔티티 중에서 처음부터 5개 이내의 목록을 반환합니다.
    List<Goods> findTop10ByName(String name);   //해당 테이블에서 매개변수로 입력된 name에 해당하는 엔티티 중에서 먼저 검색된 10개 이내의 목록을 반환합니다.
    Goods findByGnoIs(Long gno);      //해당 테이블에서 매개변수로 입력된 gno에 해당하는 엔티티를 반환합니다.
    Goods findByGnoEquals(Long gno);  //해당 테이블에서 매개변수로 입력된 gno에 해당하는 엔티티를 반환합니다.
    List<Goods> findByPriceNull();      //해당 테이블에서 Price 컬럼이 Null 값인 엔티티의 목록을 반환합니다.
    List<Goods> findByPriceIsNull();    //해당 테이블에서 Price 컬럼이 Null 값인 엔티티의 목록을 반환합니다.
    List<Goods> findByPriceNotNull();   //해당 테이블에서 Price 컬럼이 Null 값이 아닌 엔티티의 목록을 반환합니다.
    List<Goods> findByPriceIsNotNull(); //해당 테이블에서 Price 컬럼이 Null 값이 아닌 엔티티의 목록을 반환합니다.
    Goods findByGnoAndName(Long gno, String name);    //해당 테이블에서 매개변수로 입력된 gno와 name이 모두 만족하는 엔티티를 반환합니다.
    Goods findByGnoOrName(Long gno, String name);     //해당 테이블에서 매개변수로 입력된 gno와 name이 어느 하나라도 만족하는 엔티티를 반환합니다.
    List<Goods> findByPriceGreaterThan(Long price);     //해당 테이블에서 매개변수로 입력된 price 보다 큰 엔티티 목록을 반환합니다.
    List<Goods> findByPriceLessThan(Long price);        //해당 테이블에서 매개변수로 입력된 price 보다 작은 엔티티 목록을 반환합니다.
    List<Goods> findByPriceBetween(Long price1, Long price2);   //해당 테이블에서 매개변수로 입력된 price1 부터 price2 까지에 해당하는 엔티티 목록을 반환합니다.
    List<Goods> findByNameLike(String name);    //해당 테이블에서 매개변수로 입력된 name이 포함되는 엔티티 목록을 반환합니다.
    List<Goods> findByNameContains(String name);    //해당 테이블에서 매개변수로 입력된 name이 포함되는 엔티티 목록을 반환합니다.
    List<Goods> findByNameStartsWith(String name);      //해당 테이블에서 매개변수로 입력된 name으로 시작하는 엔티티 목록을 반환합니다.
    List<Goods> findByNameEndsWith(String name);        //해당 테이블에서 매개변수로 입력된 name으로 끝나는 엔티티 목록을 반환합니다.
    List<Goods> findByNameOrderByGnoAsc(String name);    //해당 테이블에서 매개변수로 입력된 name 과 일치하는 엔티티를 목록으로 반환하되, gno 컬럼을 기준으로 오름차순 정렬하여 반환합니다.
    List<Goods> findByNameOrderByGnoDesc(String name);   //해당 테이블에서 매개변수로 입력된 name 과 일치하는 엔티티를 목록으로 반환하되, gno 컬럼을 기준으로 내림차순 정렬하여 반환합니다.
}
