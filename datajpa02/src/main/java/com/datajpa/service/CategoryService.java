package com.datajpa.service;

import com.datajpa.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> categoryList();
    CategoryDto getCategory(Long id);
    void saveCategory(CategoryDto categoryDto) throws Exception;
    void changeCategory(CategoryDto categoryDto) throws Exception;
    void deleteCategory(Long id) throws Exception;
}