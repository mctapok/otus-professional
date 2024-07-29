package ru.otus.gavrilov.spring.context.repo;

import org.springframework.stereotype.Component;
import ru.otus.gavrilov.spring.context.domain.Product;

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
    public List<Product> allProducts() {
        return new ArrayList<>(products);
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
