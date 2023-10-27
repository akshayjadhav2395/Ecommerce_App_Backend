package com.myEcom.services.serviceImpl;

import com.myEcom.Exceptions.ResourceNotFoundException;
import com.myEcom.entity.Cart;
import com.myEcom.entity.User;
import com.myEcom.payload.CartDto;
import com.myEcom.payload.ItemRequest;
import com.myEcom.repository.CartRepository;
import com.myEcom.repository.ProductRepository;
import com.myEcom.repository.UserRepository;
import com.myEcom.services.CartServiceI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartServiceI {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartDto addItemToCart(ItemRequest itemRequest, String userName) {
        return null;
    }

    @Override
    public CartDto getCart(String userName) {

        User user = this.userRepository.findByEmail(userName).orElseThrow(() -> new ResourceNotFoundException("Resource with userName: " + userName + "not found..!"));
        Cart cart = this.cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Resource with given user not found..!"));

        return this.modelMapper.map(cart, CartDto.class);
    }

    @Override
    public CartDto removeItemFromCart(String userName, int productId) {
        return null;
    }
}
