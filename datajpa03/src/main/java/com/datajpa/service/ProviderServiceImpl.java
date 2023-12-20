package com.datajpa.service;

import com.datajpa.dto.ProviderDto;
import com.datajpa.entity.Provider;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProviderServiceImpl implements ProviderService {

//    EntityManagerFactory factory = Persistence.createEntityManagerFactory("provider");
//    EntityManager entityManager = factory.createEntityManager();
//    EntityTransaction tx = entityManager.getTransaction();

    @PersistenceContext
    private EntityManager entityManager;

    private final ModelMapper modelMapper;

    @Override
    public List<ProviderDto> list() {   //여러 건의 목록 검색
        List<Provider> lst = entityManager.createQuery("select p from Provider p", Provider.class).getResultList();
        List<ProviderDto> res = new ArrayList<>();
        for(Provider p:lst){
            ProviderDto pro = modelMapper.map(p, ProviderDto.class);
            res.add(pro);
        }
        return res;
    }

    @Override
    public ProviderDto get(Long id) {   //한 건의 레코드 검색
        Provider pro = entityManager.find(Provider.class, id);
        ProviderDto dto = modelMapper.map(pro, ProviderDto.class);
        return dto;
    }

    @Override
    public void save(ProviderDto dto) { //추가
        Provider entity = modelMapper.map(dto, Provider.class);
        entityManager.persist(entity);
    }

    @Override
    public void change(ProviderDto dto) {   //수정
        Provider entity = modelMapper.map(dto, Provider.class);
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {   //삭제
        Provider pro = entityManager.find(Provider.class, id);
        entityManager.remove(pro);
    }
}
