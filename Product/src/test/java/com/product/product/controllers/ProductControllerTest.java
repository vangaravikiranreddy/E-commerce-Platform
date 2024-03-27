package com.product.product.controllers;

import com.product.product.controller.ProductController;
import com.product.product.dto.GenericProductDto;
import com.product.product.exceptions.ProductNotFoundException;
import com.product.product.service.FakeProductService;
import com.product.product.service.ProductService;
import jakarta.inject.Inject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductControllerTest {
    @Inject
    ProductController productController;

    @MockBean
    ProductService productService;
    @Test
    void testGetProductByIdNegativeTC() {
        assertThrows(ProductNotFoundException.class, () -> productController.getProductById(1000L,""));
    }
    @Test
    void testGetProductWithPositiveTC() throws ProductNotFoundException {
        GenericProductDto expectedProduct = new GenericProductDto();
        expectedProduct.setId(1);
        expectedProduct.setTitle("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
        expectedProduct.setPrice(109.95);
        expectedProduct.setDescription("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday");
        expectedProduct.setImage("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");

        GenericProductDto actualProduct = productController.getProductById(1L,"");

// Use EqualsBuilder for comparison
        boolean productsAreEqual = new EqualsBuilder()
                .append(expectedProduct.getId(), actualProduct.getId())
                .append(expectedProduct.getPrice(), actualProduct.getPrice())
                .append(expectedProduct.getTitle(), actualProduct.getTitle())
                .append(expectedProduct.getDescription(), actualProduct.getDescription())
                .append(expectedProduct.getImage(), actualProduct.getImage())
                .isEquals();
        assertTrue(productsAreEqual);
    }
    @Test
    void testGetProductByIdMocking() throws ProductNotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto();
        when(productService.getProductById(10L)).thenReturn(genericProductDto);
        GenericProductDto genericProductDto1 = productController.getProductById(10L, "");
        assertEquals(genericProductDto, genericProductDto1);
    }
}
