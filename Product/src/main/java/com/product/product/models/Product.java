package com.product.product.models;

import com.product.product.dto.GenericProductDto;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;
    @ManyToOne(optional = false)
    private Category category;

    @OneToOne(cascade = jakarta.persistence.CascadeType.REMOVE, optional = false)
    @JoinColumn(nullable = false)
    private Price price;
    private int inventoryCount;

    public GenericProductDto from(Product product) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle(product.getTitle());
        return genericProductDto;
    }
}
