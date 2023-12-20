package com.datajpa.entity;

import com.datajpa.dto.GoodsDto;
import jakarta.persistence.*;
import lombok.*;
import com.datajpa.entity.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//상품(Goods)은 제공업체(Provider)와 다대일(n:1) 관계 매핑
//상품(Goods)와 제작업체(Producer)은 다대다(n:m) 관계 매핑
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gno;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer amount;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    @ToString.Exclude
    private Provider provider;

    @ManyToMany
    @ToString.Exclude
    private List<Producer> producers = new ArrayList<>();

    public void addProducer(Producer producer){
        this.producers.add(producer);
    }

    public void change(GoodsDto dto){
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.amount = dto.getAmount();
    }
}
