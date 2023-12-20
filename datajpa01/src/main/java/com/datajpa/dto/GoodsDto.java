package com.datajpa.dto;

import com.datajpa.entity.Provider;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class GoodsDto {
    private Long gno;
    private String name;
    private Integer price;
    private Integer amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Provider provider;
}
