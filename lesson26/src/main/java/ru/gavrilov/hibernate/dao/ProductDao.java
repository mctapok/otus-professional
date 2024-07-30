package ru.gavrilov.hibernate.dao;

import lombok.*;
import org.hibernate.Session;
import ru.gavrilov.hibernate.configuration.JavaBasedSessionFactory;
import ru.gavrilov.hibernate.entities.Product;
import ru.gavrilov.hibernate.entities.Purchase;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductDao {
    Session session = null;

    public List<String> getProductCustomers(long productId) {
        session = JavaBasedSessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product product = session.find(Product.class, productId);
        List<String> customers = new ArrayList<>();
        if (product != null){
            for (Purchase p : product.getPurchases()) {
                customers.add(p.getCustomer().getName() + " - " + p.getPrice());
            }
        }
        session.getTransaction().commit();
        session.close();
        return customers;
    }

    public void deleteProduct(long productId) {
        session = JavaBasedSessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product product = session.find(Product.class, productId);
        if (product != null) {
            session.remove(product);
        }
        session.getTransaction().commit();
        session.close();
    }

}
