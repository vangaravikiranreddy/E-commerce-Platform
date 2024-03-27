package com.product.product.service;

import com.product.product.dto.GenericProductDto;
import com.product.product.models.Product;
import com.product.product.models.SortParam;
import com.product.product.repositories.SearchRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private SearchRepository searchRepository;

    public SearchService(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public List<GenericProductDto> searchProducts(String query, int pageNumber, int pageSize, List<SortParam> sortParams) {
        Sort sort = null;
        if (sortParams.get(0).getSortType().equals("ASC")) {
            sort = Sort.by(sortParams.get(0).getParamName()).ascending();
        } else {
            sort = Sort.by(sortParams.get(0).getParamName()).descending();
        }
        for (int i = 1; i < sortParams.size(); i++) {
            if (sortParams.get(i).getSortType().equals("ASC")) {
                sort.and(Sort.by(sortParams.get(i).getParamName())).ascending();
            } else {
                sort.and(Sort.by(sortParams.get(i).getParamName())).descending();
            }
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        List<Product> products = searchRepository.findAllByTitleContainingIgnoreCase(query, pageRequest);
//          Pageable pageable = PageRequest.of(pageNumber, pageSize);
//          List<Product> products = searchRepository.findAllByTitleContaining(query, pageable);
          List<GenericProductDto> genericProductDtos = new ArrayList<>();
          for (Product product : products) {
              genericProductDtos.add(product.from(product));
          }
          return genericProductDtos;
    }
}
