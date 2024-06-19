package ru.otus.denis.spring_boot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.denis.spring_boot.dtos.CreateProductDto;
import ru.otus.denis.spring_boot.entities.Product;
import ru.otus.denis.spring_boot.services.ProductService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody CreateProductDto createProductDto, @RequestHeader String title) {
        return productService.createNewProduct(createProductDto, title);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(Long.parseLong(id));
    }
}
