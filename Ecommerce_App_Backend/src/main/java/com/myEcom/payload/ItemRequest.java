package com.myEcom.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemRequest {

    private int productId;
    private int quantity;
}
