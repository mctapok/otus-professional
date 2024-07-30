package ru.otus.denis.spring_boot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.denis.spring_boot.dtos.CreateProductDto;
import ru.otus.denis.spring_boot.entities.Product;
import ru.otus.denis.spring_boot.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productsRepository;

    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    public Product createNewProduct(CreateProductDto createProductDto, String title) {
        Product product = new Product(title, createProductDto.getPrice());
        product = productsRepository.create(product);
        return product;
    }

    public Product getProductById(Long id){
        return productsRepository.findById(id).orElseThrow();
    }
}
