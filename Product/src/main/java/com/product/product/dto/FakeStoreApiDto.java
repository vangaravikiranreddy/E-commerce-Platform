package com.product.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FakeStoreApiDto implements Serializable {
    private int id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
