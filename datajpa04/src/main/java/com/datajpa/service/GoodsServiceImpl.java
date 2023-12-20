package com.datajpa.service;

import com.datajpa.dto.GoodsDto;
import com.datajpa.entity.Goods;
import com.datajpa.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class GoodsServiceImpl implements GoodsService {

    private final GoodsRepository goodsRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<GoodsDto> list() {
        List<Goods> lst = goodsRepository.findAll();
        List<GoodsDto> goodsList = lst.stream().map((goods) ->
                modelMapper.map(goods, GoodsDto.class))
                .collect(Collectors.toList());
        return goodsList;
    }

    @Override
    public GoodsDto get(Long gno) {
        Optional<Goods> goods = goodsRepository.findById(gno);
        GoodsDto res = modelMapper.map(goods, GoodsDto.class);
        return res;
    }

    @Override
    public void save(GoodsDto goodsDto) {
        Goods goods = modelMapper.map(goodsDto, Goods.class);
        goodsRepository.save(goods);
    }

    @Override
    public void change(GoodsDto goodsDto) {
        Optional<Goods> goods = goodsRepository.findById(goodsDto.getGno());
        Goods res = goods.orElseThrow();
        res.change(goodsDto);
        goodsRepository.save(res);
    }

    @Override
    public void delete(Long gno) {
        goodsRepository.deleteById(gno);
    }
}
