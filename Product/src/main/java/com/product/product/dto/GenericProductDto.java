package com.product.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private int id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
