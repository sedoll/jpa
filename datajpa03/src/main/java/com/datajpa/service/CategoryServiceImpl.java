package com.datajpa.service;

import com.datajpa.dto.CategoryDto;
import com.datajpa.entity.Category;
import com.datajpa.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<CategoryDto> categoryList() {
        List<Category> cList = categoryRepository.findAll();
        List<CategoryDto> cateList = cList.stream().map((cate) ->
            modelMapper.map(cate, CategoryDto.class)
        ).collect(Collectors.toList());
        return cateList;
    }

    @Override
    public CategoryDto getCategory(Long id) {
        Category cate = categoryRepository.findOne(id);
        CategoryDto dto = modelMapper.map(cate, CategoryDto.class);
        return dto;
    }

    @Override
    public void saveCategory(CategoryDto categoryDto) throws Exception {
        Category entity = modelMapper.map(categoryDto, Category.class);
        categoryRepository.save(entity);
    }

    @Override
    public void changeCategory(CategoryDto categoryDto) throws Exception{
        Category entity = modelMapper.map(categoryDto, Category.class);
        categoryRepository.modify(entity);
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        Category cate = categoryRepository.findOne(id);
        categoryRepository.remove(cate);
    }
}
