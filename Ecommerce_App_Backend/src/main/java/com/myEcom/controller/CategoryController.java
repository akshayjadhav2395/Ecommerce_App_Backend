package com.myEcom.controller;

import com.myEcom.payload.CategoryDto;
import com.myEcom.services.serviceImpl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto)
    {
        CategoryDto savedCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory()
    {
        List<CategoryDto> categoryList = this.categoryService.getCategories();
        return new ResponseEntity<List<CategoryDto>>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable int categoryId)
    {
        CategoryDto singleCategory = this.categoryService.getSingleCategory(categoryId);
        return new ResponseEntity<CategoryDto>(singleCategory, HttpStatus.OK);
    }

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable int categoryId)
    {
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int categoryId)
    {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
