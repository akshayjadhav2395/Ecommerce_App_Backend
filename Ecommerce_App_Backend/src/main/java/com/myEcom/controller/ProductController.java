package com.myEcom.controller;

import com.myEcom.payload.ApiResponse;
import com.myEcom.payload.ProductDto;
import com.myEcom.payload.ProductResponse;
import com.myEcom.services.serviceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping("/category/{categoryId}/product")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto, @PathVariable int categoryId)
    {
        ProductDto savedProduct = this.productService.createProduct(productDto, categoryId);
        return new ResponseEntity<ProductDto>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/product")
    public ResponseEntity<ProductResponse> getProducts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    )
    {
        ProductResponse allProducts = this.productService.getAllProducts(pageNumber, pageSize);
        return new ResponseEntity<ProductResponse>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable int productId)
    {
        ProductDto singleProduct = this.productService.getSingleProduct(productId);
        return new ResponseEntity<ProductDto>(singleProduct, HttpStatus.OK);
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable int productId)
    {
        ProductDto updatedProduct = this.productService.updateProduct(productDto, productId);
        return new ResponseEntity<ProductDto>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable int productId)
    {
        this.productService.deleteProduct(productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Product Deleted Successfully...!", false) , HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/product")
    public ResponseEntity<ProductResponse> getProductByCategory(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @PathVariable int categoryId )
    {
        ProductResponse productsByCategory = this.productService.getProductsByCategory(categoryId, pageNumber, pageSize);
        return new ResponseEntity<ProductResponse>(productsByCategory, HttpStatus.OK);
    }
}
