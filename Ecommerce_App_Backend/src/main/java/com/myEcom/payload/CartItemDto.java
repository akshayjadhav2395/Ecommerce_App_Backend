package com.myEcom.payload;

import com.myEcom.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToOne;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

    private int cartItemId;
    private ProductDto product;
    private int quantity;
    private double totalProductPrice;
}
