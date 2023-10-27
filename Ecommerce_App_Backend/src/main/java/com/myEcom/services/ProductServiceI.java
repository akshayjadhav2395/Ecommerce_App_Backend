package com.myEcom.services;

import com.myEcom.payload.ProductDto;
import com.myEcom.payload.ProductResponse;

import java.util.List;

public interface ProductServiceI {

    public ProductDto createProduct(ProductDto productDto, int categoryId);
    public ProductResponse getAllProducts(int pageNumber, int pageSize, String sortBy, String sortDir);
//    public ProductResponse getProducts(int pageNumber, int pageSize, String sortBy, String sortDirection);
//    public ProductResponse getProductsByCategory(int categoryId, int pageNumber, int pageSize);
    public ProductDto getSingleProduct(int productId);
    public ProductDto updateProduct(ProductDto productDto, int productId);
    public void deleteProduct(int productId);
    public ProductResponse getProductsByCategory(int categoryId, int pageNumber, int pageSize);

}
