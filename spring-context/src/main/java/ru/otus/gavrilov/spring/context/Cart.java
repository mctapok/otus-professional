package ru.otus.gavrilov.spring.context;

import java.util.List;

public interface Cart {
    void addProduct(long id);
    void removeProduct(long id);

    List<Product> showProductsInCart();
}
