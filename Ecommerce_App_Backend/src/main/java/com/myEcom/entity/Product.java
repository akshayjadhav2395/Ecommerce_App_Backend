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
@Table(name = "ecom_products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;

    @Column(name = "product_brand_name", length = 300, unique = true)
    private String productName;
    private String productDesc;
    private double productPrice;
    private boolean stock=true;
    private boolean isLive=false;
    private String imageName;
    @ManyToOne
    private Category category;
}
