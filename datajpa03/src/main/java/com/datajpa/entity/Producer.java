package com.datajpa.entity;

import com.datajpa.dto.ProducerDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
//제작업체(Producer) 와 상품(Goods)은 다대다(n:m) 관계 매핑
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "producer")
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private String email;

    @ManyToMany
    @ToString.Exclude
    private List<Goods> goods = new ArrayList<>();

    public void addProducer(Goods pro){
        goods.add(pro);
    }

    public void change(ProducerDto dto){
        this.name = dto.getName();
        this.code = dto.getCode();
        this.email = dto.getEmail();
    }
}
