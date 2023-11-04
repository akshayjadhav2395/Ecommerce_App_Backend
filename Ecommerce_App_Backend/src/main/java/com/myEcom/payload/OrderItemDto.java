package com.myEcom.payload;

import com.myEcom.entity.Order;
import com.myEcom.entity.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Setter
@Getter
public class OrderItemDto {

    private int orderItemId;
    private OrderDto orders;
    private ProductDto product;
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
