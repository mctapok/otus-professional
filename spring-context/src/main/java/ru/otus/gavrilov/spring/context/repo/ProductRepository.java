package ru.otus.gavrilov.spring.context.repo;

import ru.otus.gavrilov.spring.context.domain.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> allProducts();

    Product getProductByID(long id);
}
