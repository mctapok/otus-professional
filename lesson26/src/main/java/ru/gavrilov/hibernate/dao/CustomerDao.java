package ru.gavrilov.hibernate.dao;

import lombok.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import ru.gavrilov.hibernate.configuration.JavaBasedSessionFactory;
import ru.gavrilov.hibernate.entities.Customer;
import ru.gavrilov.hibernate.entities.Purchase;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CustomerDao {
    private final static Log LOGGER = LogFactory.getLog(MethodHandles.lookup().lookupClass());
    Session session = null;

    public void addCustomer(Customer customer) {
        session = JavaBasedSessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Customer addedCustomer = session.merge(customer);
        session.getTransaction().commit();
        session.close();
        LOGGER.info("new customer  " + addedCustomer.toString());
    }

    public List<String> getCustomerProducts(long customerId) {
        session = JavaBasedSessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Customer customer = session.find(Customer.class, customerId);
        List<String> customerProducts = new ArrayList<>();
        if (customer != null) {
            for (Purchase p : customer.getPurchases()){
                customerProducts.add(p.getProduct().getName() + " - " + p.getPrice());
            }
        }
        session.getTransaction().commit();
        session.close();
        return customerProducts;
    }

    public void deleteCustomer(long customerId) {
        session = JavaBasedSessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Customer customer = session.find(Customer.class, customerId);
        if (customer != null) {
            session.remove(customer);
        }
        session.getTransaction().commit();
        session.close();
    }

}
