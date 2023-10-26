package com.myEcom.services.serviceImpl;


import com.myEcom.Exceptions.ResourceNotFoundException;
import com.myEcom.entity.Product;
import com.myEcom.payload.ProductDto;
import com.myEcom.repository.CategoryRepository;
import com.myEcom.repository.ProductRepository;
import com.myEcom.services.ProductServiceI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductServiceI {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Product product = this.modelMapper.map(productDto, Product.class);
        Product savedProduct = productRepository.save(product);
        return this.modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {

        List<Product> productList = productRepository.findAll();

        List<ProductDto> productDtoList = productList.stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

        return productDtoList;
    }

    @Override
    public ProductDto getSingleProduct(int productId) {
        Product singleProduct = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product with "+productId+"not found"));
        return this.modelMapper.map(singleProduct, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, int productId) {

        Product byProductId = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product with "+productId+"not found"));
        if(productDto.getProductId()!=byProductId.getProductId())
        {
            throw new ResourceNotFoundException("Product with "+productId+"not found");
        }

        byProductId.setProductPrice(productDto.getProductPrice());
        byProductId.setProductName(productDto.getProductName());
        byProductId.setProductDesc(productDto.getProductDesc());
        byProductId.setLive(productDto.isLive());
        byProductId.setStock(productDto.isStock());
        byProductId.setImageName(productDto.getImageName());
        Product updatedProduct = productRepository.save(byProductId);

        return this.modelMapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    public void deleteProduct(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with " + productId + "not found"));
        this.productRepository.delete(product);
    }


}
