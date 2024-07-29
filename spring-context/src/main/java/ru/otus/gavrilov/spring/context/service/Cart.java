package ru.otus.gavrilov.spring.context.service;

import ru.otus.gavrilov.spring.context.domain.Product;

import java.util.List;

public interface Cart {
    void addProduct(long id);
    void removeProduct(long id);

    List<Product> showProductsInCart();
}
