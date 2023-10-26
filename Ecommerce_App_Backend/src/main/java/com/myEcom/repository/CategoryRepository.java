package com.myEcom.repository;

import com.myEcom.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public Category findByCategoryId(int categoryId);
    public Category findByTitle(String title);
}
