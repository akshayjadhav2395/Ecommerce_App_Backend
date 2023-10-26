package com.myEcom.repository;

import com.myEcom.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    public Product findByProductId(int productId);
    //public Page<Product> findByCategory(Category category, Pageable pageable);

}
