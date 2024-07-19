package ru.otus.gavrilov.spring.context;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Scope("prototype")
@Component
public class CartImpl implements Cart {
    ProductRepository repository;
    List<Product> productsInCart;

    public CartImpl(ProductRepository repository) {
        this.repository = repository;
        productsInCart = new ArrayList<>();
    }

    @Override
    public void addProduct(long id) {
        Product addingProduct = repository.getProductByID(id);
        if (addingProduct != null) {
            productsInCart.add(addingProduct);
            return;
        }
        System.out.println("product not found");
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
        System.out.println("product not found");
    }
}
