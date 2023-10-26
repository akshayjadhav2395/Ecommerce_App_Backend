package com.myEcom.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartItemId;
    @OneToOne
    private Product product;
    private int quantity;
    private double totalProductPrice;
    @ManyToOne
    private Cart cart;

    public double getTotalProductPrice()
    {
        return totalProductPrice;
    }
    public void setTotalProductPrice()
    {
        this.totalProductPrice=this.product.getProductPrice()* this.quantity;
    }
}
