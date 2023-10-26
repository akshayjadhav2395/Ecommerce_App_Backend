package com.myEcom.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OrderItem {

    @Id
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
