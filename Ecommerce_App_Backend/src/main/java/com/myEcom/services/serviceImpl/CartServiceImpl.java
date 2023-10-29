package com.myEcom.services.serviceImpl;

import com.myEcom.Exceptions.ResourceNotFoundException;
import com.myEcom.entity.Cart;
import com.myEcom.entity.CartItem;
import com.myEcom.entity.Product;
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
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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
    public CartDto addItemToCart(ItemRequest itemRequest, String username) {

        int productId = itemRequest.getProductId();
        int quantity = itemRequest.getQuantity();

        if(quantity<=0)
        {
            throw new ResourceNotFoundException("Invalid Quantity...!");
        }
        
        //get the user
        User user = this.userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User with" + username + "not found"));

        //get product from db: productRepository
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with" + productId + "not found"));
        if(!product.isStock())
        {
            throw new ResourceNotFoundException("Product is out of stock!");
        }
        System.out.println(product.getProductPrice());

        //create new cartItem: with product and quantity
        CartItem cartItem=new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setTotalProductPrice();

        //getting cart from user if user don't have cart then we'll create new cart
        Cart cart = user.getCart();
        if(cart==null)
        {
            cart=new Cart();
        }
        cart.setUser(user);

        //add items into cart
        Set<CartItem> cartItems = cart.getCartItems();

        AtomicReference<Boolean> flag=new AtomicReference<>(false);

        Set<CartItem> newItems = cartItems.stream().map((i) -> {
            //changes
            if (i.getProduct().getProductId() == product.getProductId()) {
                i.setQuantity(quantity);
                i.setTotalProductPrice();
                flag.set(true);
            }
            return i;
        }).collect(Collectors.toSet());

        if(flag.get())
        {
            //new items ko items ki jagah set kiya
            cartItems.clear();
            cartItems.addAll(newItems);
        }
        else {
            cartItem.setCart(cart);
            cartItems.add(cartItem);
        }
        Cart savedCart = this.cartRepository.save(cart);

        return this.modelMapper.map(savedCart, CartDto.class);
    }

    @Override
    public CartDto getCart(String username) {

        User user = this.userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User with userName: " + username + "not found..!"));

        Cart cart = this.cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart not found..!"));

        return this.modelMapper.map(cart, CartDto.class);
    }


    @Override
    public CartDto removeItemFromCart(String username, int productId) {

        User user = this.userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User with username: " + username + "not found...!"));

        Cart cart = user.getCart();

        Set<CartItem> cartItems = cart.getCartItems();
        boolean removeIf = cartItems.removeIf(cartItem -> cartItem.getProduct().getProductId() == productId);

        System.out.println(removeIf);
        Cart updatedCart = this.cartRepository.save(cart);

        return this.modelMapper.map(updatedCart, CartDto.class);
    }
}
