package com.myEcom.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderItemId;
    @ManyToOne
    private Order order;
    @OneToOne
    private Product product;
    private int quantity;
    private double totalProductPrice;

    public double getTotalProductPrice()
    {
        return totalProductPrice;
    }
    public void setTotalProductPrice()
    {
        this.totalProductPrice=this.product.getProductPrice()* this.quantity;
    }
}
