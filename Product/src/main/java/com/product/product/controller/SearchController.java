package com.product.product.controller;

import com.product.product.dto.GenericProductDto;
import com.product.product.dto.SearchRequestDto;
import com.product.product.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("")
    public Page<GenericProductDto> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
        List<GenericProductDto> genericProductDtos = searchService.searchProducts(searchRequestDto.getQuery(), searchRequestDto.getPageNumber(), searchRequestDto.getPageSize(), searchRequestDto.getSortParams());
        Page<GenericProductDto> genericProductDtoPage = new PageImpl<>(
                genericProductDtos
        );
        return genericProductDtoPage;
    }
}
