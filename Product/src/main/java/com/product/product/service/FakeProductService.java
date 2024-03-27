package com.product.product.service;

import com.product.product.thirdpartyclient.FakeStoreClient;
import com.product.product.dto.FakeStoreApiDto;
import com.product.product.dto.GenericProductDto;
import com.product.product.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Primary
public class FakeProductService implements ProductService {
    @Value("${FakeStoreApi.Url}")
    private String apiUrl;
    @Value("${FakeStoreApi.Path}")
    private  String apiEndPoint;
    private FakeStoreClient fakeStoreClient;
    private RedisTemplate<String, FakeStoreApiDto> redisTemplate;

    public FakeProductService(FakeStoreClient fakeStoreClient, RedisTemplate redisTemplate) {
        this.fakeStoreClient = fakeStoreClient;
        this.redisTemplate = redisTemplate;
    }
    @Override
    public GenericProductDto getProductById(long id) throws ProductNotFoundException {
        FakeStoreApiDto fakeStoreApiDto = (FakeStoreApiDto) redisTemplate.opsForHash().get("PRODUCTS", id);
        if (fakeStoreApiDto != null) {
           return addGenericDetail(fakeStoreApiDto);
        }
        fakeStoreApiDto = fakeStoreClient.getProductById(apiUrl + apiEndPoint, id);
        if (fakeStoreApiDto == null) {
            throw new ProductNotFoundException("Given Product Id is not valid");
        }
        redisTemplate.opsForHash().put("PRODUCTS", id, fakeStoreApiDto);

        return addGenericDetail(fakeStoreApiDto);
    }

    @Override
    public FakeStoreApiDto[] getAllProducts() {
        return fakeStoreClient.getAllProducts(apiUrl + apiEndPoint);
    }

    @Override
    public GenericProductDto addProduct(FakeStoreApiDto fakeStoreApiDto) {
        FakeStoreApiDto fakeStoreApiDto1 = fakeStoreClient.addProduct(apiUrl + apiEndPoint, fakeStoreApiDto);
        return addGenericDetail(fakeStoreApiDto1);
    }
    @Override
    public GenericProductDto deleteProductById(long id) {
        FakeStoreApiDto fakeStoreApiDto = fakeStoreClient.deleteProductById(apiUrl + apiEndPoint, id);
        return addGenericDetail(fakeStoreApiDto);
    }

    public GenericProductDto addGenericDetail(FakeStoreApiDto fakeStoreApiDto) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreApiDto.getId());
        genericProductDto.setTitle(fakeStoreApiDto.getTitle());
        genericProductDto.setPrice(fakeStoreApiDto.getPrice());
        genericProductDto.setDescription(fakeStoreApiDto.getDescription());
        genericProductDto.setImage(fakeStoreApiDto.getImage());

        return genericProductDto;
    }

    @Override
    public GenericProductDto updateProduct(long id, FakeStoreApiDto fakeStoreApiDto) {
        FakeStoreApiDto fakeStoreApiDto1 = fakeStoreClient.updateProduct(apiUrl + apiEndPoint, id, fakeStoreApiDto);
        return addGenericDetail(fakeStoreApiDto1);
    }
}
