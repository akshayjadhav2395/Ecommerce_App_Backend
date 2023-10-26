package com.myEcom.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private int productId;
    private String productName;
    private String productDesc;
    private double productPrice;
    private boolean stock;
    private boolean isLive;
    private String imageName;
    private CategoryDto category;

}
