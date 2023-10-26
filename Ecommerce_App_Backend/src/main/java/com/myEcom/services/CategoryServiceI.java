package com.myEcom.services;


import com.myEcom.payload.CategoryDto;

import java.util.List;

public interface CategoryServiceI {

    public CategoryDto createCategory(CategoryDto categoryDto);
    public List<CategoryDto> getCategories();
    public CategoryDto getSingleCategory(int categoryId);
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);
    public void deleteCategory(int categoryId);
}
