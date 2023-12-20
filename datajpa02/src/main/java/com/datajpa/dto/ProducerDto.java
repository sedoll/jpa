package com.datajpa.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProducerDto {
    private Long id;
    private String code;
    private String name;
    private String email;
}
