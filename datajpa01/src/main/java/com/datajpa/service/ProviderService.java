package com.datajpa.service;

import com.datajpa.dto.ProviderDto;

import java.util.List;

public interface ProviderService {
    List<ProviderDto> list();
    ProviderDto get(Long id);
    void save(ProviderDto dto);
    void change(ProviderDto dto);
    void delete(Long id);
}
