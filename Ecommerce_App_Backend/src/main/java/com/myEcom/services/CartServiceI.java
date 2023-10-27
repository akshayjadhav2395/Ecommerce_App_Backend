package com.myEcom.services;

import com.myEcom.entity.Cart;
import com.myEcom.entity.CartItem;
import com.myEcom.payload.CartDto;
import com.myEcom.payload.ItemRequest;

import java.util.List;

public interface CartServiceI {

    //add item to cart

    // we'll check the availability of cart if is cart is available then we'll add item to cart,
    // otherwise we'll create cart and add item to it
    public CartDto addItemToCart(ItemRequest itemRequest, String userName);

    //get cart of user
    public CartDto getCart(String userName);

    //remove item from cart
    public CartDto removeItemFromCart(String userName, int productId);

}
