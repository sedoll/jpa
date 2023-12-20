package com.datajpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
//제품상세(ProductDetail)와 제품(Product)은 1:1 연관 관계 => 양방향 매핑
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String file;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "product_number")
    private Product product;
}
