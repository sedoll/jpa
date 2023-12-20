package com.datajpa.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDetailDto {
    private Long id;
    private String description;
    private String file;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
