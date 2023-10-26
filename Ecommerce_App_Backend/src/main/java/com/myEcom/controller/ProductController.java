package com.myEcom.controller;

import com.myEcom.payload.ApiResponse;
import com.myEcom.payload.ProductDto;
import com.myEcom.services.serviceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping("/")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto)
    {
        ProductDto savedProduct = this.productService.createProduct(productDto);
        return new ResponseEntity<ProductDto>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts()
    {
        List<ProductDto> allProducts = this.productService.getAllProducts();
        return new ResponseEntity<List<ProductDto>>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
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

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable int productId)
    {
        this.productService.deleteProduct(productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Product Deleted Successfully...!", false) , HttpStatus.OK);
    }

}
