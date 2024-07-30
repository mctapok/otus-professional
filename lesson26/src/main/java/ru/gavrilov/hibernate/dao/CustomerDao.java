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
    private Session session;

    public void addCustomer(Customer customer) {
        try (Session session = JavaBasedSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            Customer addedCustomer = session.merge(customer);
            session.getTransaction().commit();
            LOGGER.info("new customer  " + addedCustomer.toString());
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("failed to add customer, error message: " + e.getMessage());
        }
    }

    public List<String> getCustomerProducts(long customerId) {
        try (Session session = JavaBasedSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            Customer customer = session.find(Customer.class, customerId);
            List<String> customerProducts = new ArrayList<>();
            if (customer != null) {
                for (Purchase p : customer.getPurchases()) {
                    customerProducts.add(p.getProduct().getName() + " - " + p.getPrice());
                }
            }
            session.getTransaction().commit();
            return customerProducts;
        } catch (Exception e) {
            LOGGER.error("failed get customer products, error message: " + e.getMessage());
            return null;
        }
    }

    public void deleteCustomer(long customerId) {
        try (Session session = JavaBasedSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            Customer customer = session.find(Customer.class, customerId);
            if (customer != null) {
                session.remove(customer);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("failed delete customer, error message: " + e.getMessage());
        }
    }
}
