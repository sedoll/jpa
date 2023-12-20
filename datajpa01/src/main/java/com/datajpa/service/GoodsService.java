package com.datajpa.service;

import com.datajpa.dto.GoodsDto;

import java.util.List;

public interface GoodsService {
    List<GoodsDto> list();
    GoodsDto get(Long gno);
    void save(GoodsDto goodsDto);
    void change(GoodsDto goodsDto);
    void delete(Long gno);
}
