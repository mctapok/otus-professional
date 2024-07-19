package ru.otus.gavrilov.spring.context;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private final List<Product> products;

    public ProductRepositoryImpl() {
        this.products = new ArrayList<>();
        for (long i = 1; i <= 10; i++) {
            this.products.add(new Product(i, "product " + i, 19.99));
        }
    }

    @Override
    public void allProducts() {
        for (Product p : products) {
            System.out.println(p.toString());
        }
    }

    @Override
    public Product getProductByID(long id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
