package com.product.product.service;

import com.product.product.dto.FakeStoreApiDto;
import com.product.product.dto.GenericProductDto;
import com.product.product.dto.UserDto;
import com.product.product.exceptions.ProductNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class SelfProductService implements ProductService {
    private RestTemplate restTemplate;

    public SelfProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public GenericProductDto getProductById(long id) throws ProductNotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto();
        ResponseEntity<UserDto> userDtoResponseEntity = restTemplate.getForEntity("http://UserService/users/1", UserDto.class);
        return genericProductDto;
    }

    @Override
    public FakeStoreApiDto[] getAllProducts() {
        return new FakeStoreApiDto[0];
    }

    @Override
    public GenericProductDto addProduct(FakeStoreApiDto fakeStoreApiDto) {
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(long id) {
        return null;
    }

    @Override
    public GenericProductDto updateProduct(long id, FakeStoreApiDto fakeStoreApiDto) {
        return null;
    }
}
