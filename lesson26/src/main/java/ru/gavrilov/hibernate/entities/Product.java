package ru.gavrilov.hibernate.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
public final class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long price;

//    @ManyToMany(mappedBy = "products")
//    Set<Customer> customers = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Purchase> purchases = new HashSet<>();

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
