# 03. Repository를 사용자화(Customizing) 하는 QueryDSL(Query Domain Specific Language)

<br>

## 03-1. QueryDSL(Query Domain Specific Language)

Spring Boot JPA 에서 QueryDSL(Query Domain Specific Language)은 자바의 문법으로 쿼리문(SQL Sentence)을 생성해내는 라이브러리입니다.

- JPQL 에 비해 타입의 안정성이 좋습니다.
- JPQL 에 비해 동적쿼리 작성이 쉽습니다.
- 직관적인 코드로 작성하므로 가독성이 좋습니다.
- 자바 코드와 같은 방식이므로 유지보수성이 용이합니다.
- 고급 쿼리, 조인, 서브 쿼리, 그룹화 등이 JPQL 에 비해 쉽고 다양하게 지원합니다.

<br>

### 03-1-1. JPAQuery와 JPAClause 활용

<br>

#### JPAQuery의 메소드

| 메소드명 | 반환타입 | 설명 |
|----------------------|-----------------|-----------------------------------------------------------------------|
| clone(entityManager) | JPAQuery<T> | 주어진 EntityManager를 사용하여 이 쿼리의 상태를 새 인스턴스로 복제합니다. |
| select(expression) | JPAQuery<T> | 해당 엔티티에서 expression에 지정된 컬럼을 조회합니다. |
| createQuery("SQL Sentence") | Query | 해당 엔티티의 검색할 SQL 문장을 만듭니다. |
| fetch() | List<T> | 앞서 지정한 테이블의 모든 레코드 목록으로 반환합니다. |
| fetchCount() | long | 앞서 지정한 테이블의 전체 레코드 수를 반환합니다. |
| fetchOne() | T | 앞서 지정한 테이블과 조건으로 한 건의 레코드만을 검색하여 반환합니다. |
| fetchResults() | QueryResults<T> | 앞서 지정한 테이블의 모든 레코드 결과셋을 반환합니다. |
| fetchAll() | Q | 앞서 지정한 테이블의 모든 레코드를 Q객체로 반환합니다. |
| fetchJoin() | Q | 앞서 조인한 내용대로 검색하여 Q객체로 반환합니다. | 
| from(EntityPath<?> arg) | Q | 검색 대상의 테이블을 지정합니다. |
| innerJoin(CollectionExpression<?,P> target) | <P> Q | 지정된 대상을 사용하여 내부 조인을 만들고 fetchJoin()을 사용하여 이 조인에 fetchJoin 매개변수를 추가합니다. |
| join(CollectionExpression<?,P> target) | <P> Q | 지정된 대상을 사용하여 조인을 만들고 fetchJoin()을 사용하여 이 조인에 fetchJoin 매개변수를 추가합니다. |
| leftJoin(CollectionExpression<?,P> target) | <P> Q | 지정된 대상을 사용하여 왼쪽 조인을 만들고 fetchJoin()을 사용하여 이 조인에 fetchJoin 매개변수를 추가합니다. |
| rightJoin(CollectionExpression<?,P> target) | <P> Q | 지정된 대상을 사용하여 오른쪽 조인을 만들고 fetchJoin()을 사용하여 이 조인에 fetchJoin 매개변수를 추가합니다. |
| on(Predicate condition) | Q | 두 조인되는 엔티티 또는 테이블 간의 조인 컬럼을 지정합니다. |
| contains(Expression) | Q | 앞서 지정한 검색 조건의 일부가 포함되어 있어야 할 문자열 또는 조건을 지정합니다.  |
| eq(Expression) | Q | 앞서 지정한 컬럼의 값과 일치해야하는 값 또는 매개변수를 할당합니다. |
| exists(Expression) | Q | 앞서 지정한 컬럼이 존재 여부를 반환합니다. |
| gt(Expression) | Q | 앞서 지정한 컬럼이 Expression 의 값을 초과 조건을 지정합니다. |
| in(Expressions) | Q | 앞서 지정한 컬럼이 여러 Expression 에 어느 하나라도 만족해야할 조건들을 나열합니다. |
| isNotNull()  | Q | 앞서 지정한 컬럼이 Null이 아닐 조건을 제시합니다. |
| isNull() | Q | 앞서 지정한 컬럼이 Null 인 경우의 조건을 제시합니다. |
| lt(Expression) | Q | 앞서 지정한 컬럼이 Expression 의 값을 미만 조건을 지정합니다. |
| ne(Expression) | Q | 앞서 지정한 컬럼의 값과 불일치해야하는 값 또는 매개변수를 할당합니다. |
| notExists(Expression) | boolean | 앞서 지정한 컬럼이 존재 하지 않을 경우 true를 반환합니다. |
| distinct(Expression) | Q | 조회되는 컬럼의 중복을 제거하여 반환합니다. |
| groupBy(Expression) | Q | 그룹화 해야할 항목을 지정합니다. |
| having(Expression) | Q | 그룹화할 구문에서의 검색 조건을 지정합니다. |
| limit(long, long) | Q | 검색 결과에서 필요한 구간을 제한합니다. |
| offset(long) | Q | 검색 결과에서 시작 위치값을 지정합니다. |
| orderBy(Expression) | Q | 검색 결과를 정렬해야 할 경우 정렬기준과 기준 컬럼을 지정합니다. |
| set(Path<T> path, Expression) | Q |  처리해야할 Q객체의 필드와 값을 설정합니다. |
| where(Expression) | Q | 검색해야할 조건을 설정합니다. |


<br>

#### JPAInsertClause의 메소드

| 메소드명 | 반환타입 | 설명 |
|----------------------|-----------------|-----------------------------------------------------------------------|
| clone(entityManager) | JPAInsertClause | 주어진 EntityManager를 사용하여 이 쿼리의 상태를 새 인스턴스로 복제합니다. |
| select(expression) | JPAInsertClause | 해당 엔티티에서 expression에 지정된 컬럼을 조회합니다. |
| columns(column1, column2,...) |JPAInsertClause | 추가할 엔티티의 컬럼을 지정합니다. |
| execute() | long | 앞서 구성한 Query 를 실행합니다. |
| isEmpty() | boolean | 앞서 제시한 컬럼이나 내용이 비어있으면 true를 반환합니다. |
| select(SubQueryExpression) | JPAInsertClause | 추가할 내용을 다른 엔티티나 서브 쿼리에서 가져옵니다. |
| set(Path<T> path, Expression) | <T> JPAInsertClause | 처리해야할 Q객체의 필드와 값을 설정합니다. |
| setLockMode(lockMode) | JPAInsertClause | 지정한 LockMode로 전환합니다. |
| setNull(Path<T> path) | <T> JPAInsertClause | 처리해야할 내용을 Null로 만듭니다. |
| toString() | String | 앞서서 처리한 Query의 내용을 String으로 변환합니다. |
| values(value1, value2,..) | JPAInsertClause | 추가할 엔티티의 해당 컬럼의 값을 지정합니다. |

<br>

#### JPAUpdateClause의 메소드

| 메소드명 | 반환타입 | 설명 |
|----------------------|-----------------|-----------------------------------------------------------------------|
| execute() | long | 앞서 구성한 Query 를 실행합니다. |
| isEmpty() | boolean | 앞서 제시한 컬럼이나 내용이 비어있으면 true를 반환합니다. |
| set(Path<T> path, Expression) | <T> JPAInsertClause | 처리해야할 Q객체의 필드와 값을 설정합니다. |
| setLockMode(lockMode) | JPAInsertClause | 지정한 LockMode로 전환합니다. |
| setNull(Path<T> path) | <T> JPAInsertClause | 처리해야할 내용을 Null로 만듭니다. |
| toString() | String | 앞서서 처리한 Query의 내용을 String으로 변환합니다. |
| where(Expression) | Q | 수정해야할 조건을 설정합니다. |

<br>

#### JPADeleteClause의 메소드

| 메소드명 | 반환타입 | 설명 |
|----------------------|-----------------|-----------------------------------------------------------------------|
| execute() | long | 앞서 구성한 Query 를 실행합니다. |
| setLockMode(lockMode) | JPAInsertClause | 지정한 LockMode로 전환합니다. |
| toString() | String | 앞서서 처리한 Query의 내용을 String으로 변환합니다. |
| where(Expression) | Q | 수정해야할 조건을 설정합니다. |

<br>

#### JPAQuery/JPAInsertClause/JPAUpdateClause/JPADeleteClause 의 예제

```java
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
```

<br>

```java
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
```

<br><hr><br>

### 03-1-2. JPAQueryFactory 활용

<br>

#### JPAQueryFactory 의 메소드

| 메소드명 | 반환타입 | 설명 |
|----------------------|-----------------|-----------------------------------------------------------------------|
| clone(entityManager) | JPAQuery<T> | 주어진 EntityManager를 사용하여 이 쿼리의 상태를 새 인스턴스로 복제합니다. |
| insert(EntityPath<?> path) | JPAInsertClause | 주어진 내용으로 해당 엔티티를 추가합니다. |
| update(EntityPath<?> path) | JPAUpdateClause | 주어진 내용으로 해당 엔티티의 내용을 추가합니다. |
| delete(EntityPath<?> path) | JPADeleteClause | 주어진 내용으로 해당 엔티티를 삭제합니다. |
| select(expression) | JPAQuery<T> | 해당 엔티티에서 expression에 지정된 컬럼을 조회합니다. |
| selectFrom(EntityPath<T> from) | 주어진 소스와 프로젝션을 사용하여 새 JPQLQuery 자바 객체를 만듭니다. |
| selectOne() | 앞서 지정한 조건으로 한 건에 해당하는 엔티티만 검색합니다. |
| query() | JPAQuery<?> | 해당 엔티티의 검색할 SQL 문장을 만듭니다. |
| fetch() | List<T> | 앞서 지정한 테이블의 모든 레코드 목록으로 반환합니다. |
| fetchCount() | long | 앞서 지정한 테이블의 전체 레코드 수를 반환합니다. |
| fetchOne() | T | 앞서 지정한 테이블과 조건으로 한 건의 레코드만을 검색하여 반환합니다. |
| fetchResults() | QueryResults<T> | 앞서 지정한 테이블의 모든 레코드 결과셋을 반환합니다. |
| fetchAll() | Q | 앞서 지정한 테이블의 모든 레코드를 Q객체로 반환합니다. |
| fetchJoin() | Q | 앞서 조인한 내용대로 검색하여 Q객체로 반환합니다. | 
| from(EntityPath<?> arg) | Q | 검색 대상의 테이블을 지정합니다. |
| innerJoin(CollectionExpression<?,P> target) | <P> Q | 지정된 대상을 사용하여 내부 조인을 만들고 fetchJoin()을 사용하여 이 조인에 fetchJoin 매개변수를 추가합니다. |
| join(CollectionExpression<?,P> target) | <P> Q | 지정된 대상을 사용하여 조인을 만들고 fetchJoin()을 사용하여 이 조인에 fetchJoin 매개변수를 추가합니다. |
| leftJoin(CollectionExpression<?,P> target) | <P> Q | 지정된 대상을 사용하여 왼쪽 조인을 만들고 fetchJoin()을 사용하여 이 조인에 fetchJoin 매개변수를 추가합니다. |
| rightJoin(CollectionExpression<?,P> target) | <P> Q | 지정된 대상을 사용하여 오른쪽 조인을 만들고 fetchJoin()을 사용하여 이 조인에 fetchJoin 매개변수를 추가합니다. |
| on(Predicate condition) | Q | 두 조인되는 엔티티 또는 테이블 간의 조인 컬럼을 지정합니다. |
| contains(Expression) | Q | 앞서 지정한 검색 조건의 일부가 포함되어 있어야 할 문자열 또는 조건을 지정합니다.  |
| eq(Expression) | Q | 앞서 지정한 컬럼의 값과 일치해야하는 값 또는 매개변수를 할당합니다. |
| exists(Expression) | Q | 앞서 지정한 컬럼이 존재 여부를 반환합니다. |
| gt(Expression) | Q | 앞서 지정한 컬럼이 Expression 의 값을 초과 조건을 지정합니다. |
| in(Expressions) | Q | 앞서 지정한 컬럼이 여러 Expression 에 어느 하나라도 만족해야할 조건들을 나열합니다. |
| isNotNull()  | Q | 앞서 지정한 컬럼이 Null이 아닐 조건을 제시합니다. |
| isNull() | Q | 앞서 지정한 컬럼이 Null 인 경우의 조건을 제시합니다. |
| lt(Expression) | Q | 앞서 지정한 컬럼이 Expression 의 값을 미만 조건을 지정합니다. |
| ne(Expression) | Q | 앞서 지정한 컬럼의 값과 불일치해야하는 값 또는 매개변수를 할당합니다. |
| notExists(Expression) | boolean | 앞서 지정한 컬럼이 존재 하지 않을 경우 true를 반환합니다. |
| distinct(Expression) | Q | 조회되는 컬럼의 중복을 제거하여 반환합니다. |
| groupBy(Expression) | Q | 그룹화 해야할 항목을 지정합니다. |
| having(Expression) | Q | 그룹화할 구문에서의 검색 조건을 지정합니다. |
| limit(long, long) | Q | 검색 결과에서 필요한 구간을 제한합니다. |
| offset(long) | Q | 검색 결과에서 시작 위치값을 지정합니다. |
| orderBy(Expression) | Q | 검색 결과를 정렬해야 할 경우 정렬기준과 기준 컬럼을 지정합니다. |
| set(Path<T> path, Expression) | Q |  처리해야할 Q객체의 필드와 값을 설정합니다. |
| where(Expression) | Q | 검색해야할 조건을 설정합니다. |

<br>

#### JPAQueryFactory 의 사용 예제

```java
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
```

<br>

```java
package com.datajpa.repository;

import com.datajpa.entity.Goods;
import com.datajpa.entity.Product;
import com.datajpa.entity.QGoods;
import com.datajpa.entity.QProduct;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
@Slf4j
public class GoodsRepositoryImpl implements GoodsRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Goods> list() {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QGoods qGoods = QGoods.goods;
        List<Goods> goodsList = query
                .selectFrom(qGoods)
                .orderBy(qGoods.gno.desc())
                .fetch();
        return goodsList;
    }

    @Override
    public Goods get(Long gno) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QGoods qGoods = QGoods.goods;
        Goods goods = query
                .selectFrom(qGoods)
                .where(qGoods.gno.eq(gno))
                .orderBy(qGoods.gno.desc())
                .fetchOne();
        return goods;
    }

    @Override
    public List<Goods> findAllByName(String name) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QGoods qGoods = QGoods.goods;
        List<Goods> goodsList = query
                .selectFrom(qGoods)
                .where(qGoods.name.eq(name))
                .orderBy(qGoods.gno.desc())
                .fetch();
        return goodsList;
    }

    @Override
    public List<Goods> findAllByNameContains(String name) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QGoods qGoods = QGoods.goods;
        List<Goods> goodsList = query
                .selectFrom(qGoods)
                .where(qGoods.name.contains(name))
                .orderBy(qGoods.gno.desc())
                .fetch();
        return goodsList;
    }

    @Override
    public void insert(Goods goods) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        LocalDateTime now = LocalDateTime.now();
        QGoods qGoods = QGoods.goods;
        jpaQueryFactory.insert(qGoods)
                .columns(qGoods.name, qGoods.price, qGoods.amount, qGoods.createdAt)
                .values(goods.getName(), goods.getPrice(), goods.getAmount(), now)
                .execute();
    }

    @Override
    public void update(Goods goods) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        LocalDateTime now = LocalDateTime.now();
        QGoods qGoods = QGoods.goods;
        jpaQueryFactory.update(qGoods)
                .where(qGoods.gno.eq(goods.getGno()))
                .set(qGoods.name, goods.getName())
                .set(qGoods.price, goods.getPrice())
                .set(qGoods.amount, goods.getAmount())
                .set(qGoods.updatedAt, now)
                .execute();
    }

    @Override
    public void delete(Long gno) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QGoods qGoods = QGoods.goods;
        jpaQueryFactory.delete(qGoods)
                .where(qGoods.gno.eq(gno))
                .execute();
    }
}
```



<br><hr><br>

## 03-2. QuerydslRepositorySupport와 JpaRepository를 활용

```java
public interface ReviewRepository extends JpaRepository<Review,Long> {}
```

<br>

```java
@Repository
public class ReviewRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public StoreRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Review.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
    public List<Review> findByUser(User user){
        return jpaQueryFactory.selectFrom(review)
                .where(
                        review.user.eq(user)
                )
                .orderBy(review.createdAt.desc())
                .fetch();
    }
	...
}
```

<br><hr><br>

## 03-3. JpaRepository를 활용한 QueryDSL 적용

```java
@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>,ReviewRepositoryCustom {}
```

<br>

```java
public interface ReviewRepositoryCustom  {
    List<Review> findByUser(User user);
    List<Review> findByStore(Store store);
    List<Review> findAllByIsBlindFalse();
}
```

<br>

```java
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    //리뷰 조회 - userID
    @Override
    public List<Review> findByUser(User user){
        return jpaQueryFactory.selectFrom(review)
                .where(
                        review.user.eq(user)
                )
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}
```

<br><hr><br>

## 03-4. JpaRepository에서 클래스 상속없이 QueryDSL 적용

```java
package com.datajpa.repository;

import com.datajpa.entity.Product;

import java.util.List;

public interface ProductMapper {
    List<Product> productList1();
    List<Product> productList2();
    List<String> productList3();
    Product getProduct1(Long number);
    Product getProduct2(Long number);
    void insertProduct(Product pro);
    void updateProduct(Product pro);
    void deleteProduct(Long number);
}
```

<br>

```java
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
```


