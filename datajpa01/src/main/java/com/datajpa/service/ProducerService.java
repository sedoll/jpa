package com.datajpa.service;

import com.datajpa.dto.ProducerDto;

import java.util.List;

public interface ProducerService {
    List<ProducerDto> list();
    ProducerDto get(Long id);
    void save(ProducerDto dto);
    void change(ProducerDto dto);
    void delete(Long id);
}
