package com.datajpa.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProviderDto {
    private Long id;
    private String name;
    private String tel;
    private String addr;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
