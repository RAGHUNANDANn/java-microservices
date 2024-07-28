package com.raghu.microservices.product.service;

import com.raghu.microservices.product.dto.ProductRequest;
import com.raghu.microservices.product.dto.ProductResponse;
import com.raghu.microservices.product.model.Product;
import com.raghu.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private  final ProductRepository productRepository;

    public Product addProduct(ProductRequest productRequest){
        Product product= Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Saved new product");
        return product;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice())).toList();
    }
}
