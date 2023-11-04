package com.myEcom.services.serviceImpl;

import com.myEcom.Exceptions.ResourceNotFoundException;
import com.myEcom.entity.*;
import com.myEcom.payload.OrderDto;
import com.myEcom.payload.OrderRequest;
import com.myEcom.payload.UserDto;
import com.myEcom.repository.CartRepository;
import com.myEcom.repository.OrderRepository;
import com.myEcom.repository.UserRepository;
import com.myEcom.services.OrderServiceI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderServiceI {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderDto createOrder(OrderRequest orderRequest, String username) {

        User user = this.userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User with username: " + username + " not found...!"));

        String address = orderRequest.getAddress();
        int cartId = orderRequest.getCartId();

        Cart cart = this.cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Resource with cartId: " + cartId + "not found...!"));
        Set<CartItem> cartItems = cart.getCartItems();

        Order order=new Order();

        AtomicReference<Double> totalOrderPrice=new AtomicReference<>(0.0);
        //converting cartItems to orderItems and getting orderItem
        Set<OrderItem> orderItems = cartItems.stream().map((cartItem)-> {
            OrderItem orderItem=new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalProductPrice(cartItem.getTotalProductPrice());

            totalOrderPrice.set(totalOrderPrice.get()+orderItem.getTotalProductPrice());

            return orderItem;
        }).collect(Collectors.toSet());

        //now saving order with orderItems
        order.setOrderItem(orderItems);
        order.setUser(user);
        order.setBillingAddress(address);
        order.setPaymentStatus("NOT PAID..!");
        order.setTotalAmount(totalOrderPrice.get());
        order.setOrderCreated(new Date());
        order.setOrderDelivered(null);
        order.setOrderStatus("CREATED..!");

        Order savedOrder = this.orderRepository.save(order);

        cart.getCartItems().clear();

        this.cartRepository.save(cart);

        return this.modelMapper.map(savedOrder, OrderDto.class);
    }

    @Override
    public List<OrderDto> getAllOrder() {

        List<Order> orderList = this.orderRepository.findAll();
        List<OrderDto> orderDtoList = orderList.stream().map((order) -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());

        return orderDtoList;
    }

    @Override
    public OrderDto getSingleOrder(int orderId) {

        Order order = this.orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order with orderId: " + orderId + "not found..!"));

        return this.modelMapper.map(order, OrderDto.class);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto, int orderId) {

        Order order = this.orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order with orderId: " + orderId + "not found...!"));

        order.setOrderStatus(orderDto.getOrderStatus());
        order.setPaymentStatus(orderDto.getPaymentStatus());

        Order updatedOrder = this.orderRepository.save(order);

        return this.modelMapper.map(updatedOrder, OrderDto.class);
    }

    @Override
    public void deleteOrder(int orderId) {
        Order order = this.orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order with orderId: " + orderId + "not found...!"));
        this.orderRepository.delete(order);
    }

    @Override
    public List<OrderDto> getOrderOfUser(String username) {

        User user = this.userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User with username: " + username + "not found..!"));

        List<Order> orderList = this.orderRepository.findByUser(user);

        List<OrderDto> dtoList = orderList.stream().map((order) -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());

        return dtoList;
    }
}
