package com.myEcom.payload;

import com.myEcom.entity.OrderItem;
import com.myEcom.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private int orderId;
    private String orderStatus;
    private String paymentStatus;
    private Date orderCreated;
    private double totalAmount;
    private String billingAddress;
    private Date orderDelivered;
    private UserDto user;
    private Set<OrderItemDto> orderItem = new HashSet<>();

}
