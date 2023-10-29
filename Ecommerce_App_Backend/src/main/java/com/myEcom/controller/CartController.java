package com.myEcom.controller;

import com.myEcom.payload.CartDto;
import com.myEcom.payload.ItemRequest;
import com.myEcom.services.serviceImpl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    String username="akshayd@test.com";
    @Autowired
    private CartServiceImpl cartServiceI;


    @PostMapping("/")
     public ResponseEntity<CartDto> addItemToCart(@RequestBody ItemRequest itemRequest)
     {
         //authentication: dynamic
         CartDto cartDto = this.cartServiceI.addItemToCart(itemRequest, username);
         return new ResponseEntity<CartDto>(cartDto, HttpStatus.CREATED);
     }

     @GetMapping("/")
     public ResponseEntity<CartDto> getCart()
     {
         CartDto cartDto = this.cartServiceI.getCart(username);
         return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
     }

    @PutMapping("/{productId}")
    public ResponseEntity<CartDto> removeProductFromCart(@PathVariable int productId)
    {
        CartDto cartDto = cartServiceI.removeItemFromCart(username, productId);
        return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
    }

}
