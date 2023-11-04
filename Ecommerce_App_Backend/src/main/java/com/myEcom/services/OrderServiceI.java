package com.myEcom.services;

import com.myEcom.payload.OrderDto;
import com.myEcom.payload.OrderRequest;

import java.util.List;

public interface OrderServiceI {

    public OrderDto createOrder(OrderRequest orderRequest, String username);

    public List<OrderDto> getAllOrder();

    public OrderDto getSingleOrder(int orderId);

    public OrderDto updateOrder(OrderDto orderDto, int orderId);

    public void deleteOrder(int orderId);

    public List<OrderDto> getOrderOfUser(String username);

}
