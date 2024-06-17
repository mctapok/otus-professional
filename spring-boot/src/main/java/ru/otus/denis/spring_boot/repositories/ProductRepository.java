package ru.otus.denis.spring_boot.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.otus.denis.spring_boot.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        this.products.add(new Product(1L, "product 1", 10));
        this.products.add(new Product(2L, "product 2", 10));
        this.products.add(new Product(3L, "product 3", 10));
        this.products.add(new Product(4L, "product 4", 10));
    }

    public List<Product> findAll() {
        return products;
    }
    public Product create(Product product) {
        Long newId = products.size() + 1L;
        product.setId(newId);
        products.add(product);
        return product;
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findAny();
    }
}
