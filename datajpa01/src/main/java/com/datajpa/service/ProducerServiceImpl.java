package com.datajpa.service;


import com.datajpa.dto.ProducerDto;
import com.datajpa.entity.Producer;
import com.datajpa.repository.ProducerRepository;
import com.datajpa.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProducerServiceImpl implements ProducerService {

    private final ProducerRepository producerRepository;

    @Override
    public List<ProducerDto> list() {
        List<Producer> lst = producerRepository.findAll();
        List<ProducerDto> proList = new ArrayList<>();
        for(Producer p:lst){
            ProducerDto pro = ProducerDto.builder()
                    .name(p.getName())
                    .email(p.getEmail())
                    .code(p.getCode())
                    .build();
            proList.add(pro);
        }
        return proList;
    }

    @Override
    public ProducerDto get(Long id) {
        Optional<Producer> p = producerRepository.findById(id);
        ProducerDto pro = ProducerDto.builder()
                .name(p.get().getName())
                .email(p.get().getEmail())
                .code(p.get().getCode())
                .build();
        return pro;
    }

    @Override
    public void save(ProducerDto dto) {  //Producer 상단 위에 @Builder가 선언되지 못한 경우 직접 값 세팅
        Producer p = new Producer();
        p.setName(dto.getName());
        p.setEmail(dto.getEmail());
        p.setCode(dto.getCode());
        Producer result = producerRepository.save(p);
        log.info(result.toString());
    }

    @Override
    public void change(ProducerDto dto) {
        Optional<Producer> p = producerRepository.findById(dto.getId());
        Producer pro = p.orElseThrow();
        p.get().change(dto);
        producerRepository.save(pro);
    }

    @Override
    public void delete(Long id) {
        producerRepository.deleteById(id);
    }
}
