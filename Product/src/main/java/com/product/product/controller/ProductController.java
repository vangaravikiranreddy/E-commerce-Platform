package com.product.product.controller;

import com.product.product.dto.FakeStoreApiDto;
import com.product.product.dto.GenericProductDto;
import com.product.product.dto.UserDto;
import com.product.product.exceptions.ProductNotFoundException;
import com.product.product.models.SessionStatus;
import com.product.product.service.ProductService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private RestTemplate restTemplate;

    public ProductController(ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") long id, @CookieValue(name = "auth-token", defaultValue = "defaultValue") String cookieValue) throws ProductNotFoundException {

        // Create HttpHeaders object and set the cookie value
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.COOKIE, "auth-token=" + cookieValue);

        // Create HttpEntity with headers
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        // Make the request with exchange method
        ResponseEntity<SessionStatus> responseEntity = restTemplate.exchange(
                "http://AuthService/auth/validate",
                HttpMethod.POST,
                requestEntity,
                SessionStatus.class
        );
        SessionStatus sessionStatus = responseEntity.getBody();
        System.out.println(sessionStatus);
        if (!sessionStatus.equals(SessionStatus.ACTIVE)) {
            return null;
        }
        return productService.getProductById(id);
    }
    @GetMapping("")
    public FakeStoreApiDto[] getAllProducts() {
        return productService.getAllProducts();
    }
    @PostMapping("")
    public GenericProductDto addProduct(@RequestBody FakeStoreApiDto fakeStoreApiDto) {
        return productService.addProduct(fakeStoreApiDto);
    }
    @DeleteMapping("/{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") long id) {
        return productService.deleteProductById(id);
    }
    @PutMapping("/{id}")
    public GenericProductDto updateProduct(@PathVariable("id") long id, @RequestBody FakeStoreApiDto fakeStoreApiDto) {
        return productService.updateProduct(id, fakeStoreApiDto);
    }
}
