package ru.otus.gavrilov.spring.context;

public interface ProductRepository {
    void allProducts();

    Product getProductByID(long id);
}
