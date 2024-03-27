package com.product.product.thirdpartyclient;

import com.product.product.dto.FakeStoreApiDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreClient {
    RestTemplateBuilder restTemplateBuilder;
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreApiDto getProductById(String url, long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreApiDto> responseEntity = restTemplate.getForEntity(url + "{id}", FakeStoreApiDto.class, id);

        return responseEntity.getBody();
    }
    public FakeStoreApiDto[] getAllProducts(String url) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreApiDto[]> responseEntity = restTemplate.getForEntity(url, FakeStoreApiDto[].class);
        return responseEntity.getBody();
    }
    public FakeStoreApiDto addProduct(String url, FakeStoreApiDto fakeStoreApiDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreApiDto> responseEntity = restTemplate.postForEntity(url, fakeStoreApiDto, FakeStoreApiDto.class);
        return responseEntity.getBody();
    }
    public FakeStoreApiDto deleteProductById(String url, long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreApiDto> responseEntity = restTemplate.getForEntity(url + "{id}", FakeStoreApiDto.class, id);
        return responseEntity.getBody();
    }
    public FakeStoreApiDto updateProduct(String url, long id, FakeStoreApiDto fakeStoreApiDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreApiDto> responseEntity = restTemplate.exchange(
                url + "{id}",
                HttpMethod.PUT,
                new HttpEntity<>(fakeStoreApiDto),
                FakeStoreApiDto.class,
                id
        );
        return responseEntity.getBody();
    }
}
