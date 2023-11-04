package com.myEcom.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequest {

    private String address;
    private int cartId;
}
