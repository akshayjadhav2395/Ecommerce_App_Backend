package com.myEcom.services;

import com.myEcom.payload.ProductDto;

import java.util.List;

public interface ProductServiceI {

    public ProductDto createProduct(ProductDto productDto);
    public List<ProductDto> getAllProducts();
//    public ProductResponse getProducts(int pageNumber, int pageSize, String sortBy, String sortDirection);
//    public ProductResponse getProductsByCategory(int categoryId, int pageNumber, int pageSize);
    public ProductDto getSingleProduct(int productId);
    public ProductDto updateProduct(ProductDto productDto, int productId);
    public void deleteProduct(int productId);

}
