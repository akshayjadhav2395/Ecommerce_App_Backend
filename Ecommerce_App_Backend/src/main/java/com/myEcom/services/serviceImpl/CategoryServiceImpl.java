package com.myEcom.services.serviceImpl;

import com.myEcom.Exceptions.ResourceNotFoundException;
import com.myEcom.entity.Category;
import com.myEcom.payload.CategoryDto;
import com.myEcom.repository.CategoryRepository;
import com.myEcom.services.CategoryServiceI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryServiceI {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category savedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {

        List<Category> allCategories = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos = allCategories.stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());

        return categoryDtos;
    }

    @Override
    public CategoryDto getSingleCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category with " + categoryId + "not found"));

        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category with " + categoryId + "not found"));
        category.setTitle(categoryDto.getTitle());
        Category updatedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category with " + categoryId + "not found"));
        this.categoryRepository.delete(category);
    }
}
