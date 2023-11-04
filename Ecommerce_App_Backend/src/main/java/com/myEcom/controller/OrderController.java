package com.myEcom.controller;

import com.myEcom.payload.OrderDto;
import com.myEcom.payload.OrderRequest;
import com.myEcom.services.serviceImpl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    String username="akshayd@test.com";

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest orderRequest)
    {
        OrderDto orderDto = this.orderService.createOrder(orderRequest, username);
        return new ResponseEntity<OrderDto>(orderDto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderDto>> getAllOrder()
    {
        List<OrderDto> allOrder = this.orderService.getAllOrder();
        return new ResponseEntity<List<OrderDto>>(allOrder, HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderDto> getSingleOrder(@PathVariable int orderId)
    {
        OrderDto singleOrder = this.orderService.getSingleOrder(orderId);
        return new ResponseEntity<OrderDto>(singleOrder, HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto, @PathVariable int orderId)
    {
        OrderDto updatedOrder = this.orderService.updateOrder(orderDto, orderId);
        return new ResponseEntity<OrderDto>(updatedOrder, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<OrderDto>> getOrderOfUser()
    {
        List<OrderDto> orderOfUser = this.orderService.getOrderOfUser(username);
        return new ResponseEntity<List<OrderDto>>(orderOfUser, HttpStatus.OK);
    }
}
