package ru.otus.denis.spring_boot.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private double price;

    public Product(String title, long price){
        this.title = title;
        this.price = price;
    }
}
