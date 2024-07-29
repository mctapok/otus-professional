package ru.otus.gavrilov.spring.context.service;

import org.apache.logging.log4j.LogManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.otus.gavrilov.spring.context.domain.Product;
import ru.otus.gavrilov.spring.context.repo.ProductRepository;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Scope("prototype")
@Component
public class CartService implements Cart {
    ProductRepository repository;
    List<Product> productsInCart;

    private static final Logger logger = LogManager.getLogger("logger");

    public CartService(ProductRepository repository) {
        this.repository = repository;
        this.productsInCart = new ArrayList<>();
    }

    @Override
    public void addProduct(long id) {
        Product addingProduct = repository.getProductByID(id);
        if (addingProduct != null) {
            productsInCart.add(addingProduct);
            return;
        }
        logger.info("product with id: {} - not found ", id);
    }

    public List<Product> showProductsInCart() {
        return productsInCart;
    }

    @Override
    public void removeProduct(long id) {
        Product removingProduct = repository.getProductByID(id);
        if (removingProduct != null) {
            productsInCart.remove(removingProduct);
            return;
        }
        logger.info("product with id: {} - not found ", id);
    }
}
