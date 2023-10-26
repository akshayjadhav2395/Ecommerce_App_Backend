package com.myEcom.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ecom_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private String orderStatus;
    private String paymentStatus;
    private Date orderCreated;
    private double totalAmount;
    private String billingAddress;
    private Date orderDelivered;
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems=new HashSet<>();
}
