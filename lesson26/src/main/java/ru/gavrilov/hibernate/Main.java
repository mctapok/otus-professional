package ru.gavrilov.hibernate;


import org.hibernate.Session;
import ru.gavrilov.hibernate.configuration.JavaBasedSessionFactory;
import ru.gavrilov.hibernate.dao.CustomerDao;
import ru.gavrilov.hibernate.dao.ProductDao;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static CustomerDao customerDAO = new CustomerDao();
    private static ProductDao productDao = new ProductDao();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        dbInit();
        while (true) {
            System.out.println("""
                    enter command
                    1: show products by customer ID
                    2: show customers by product ID
                    3: delete product by ID
                    4: delete customer by ID
                    """);
            int command = Integer.parseInt(scanner.nextLine());
            switch (command) {
                case 1:
                    showProductsByCustomers();
                    break;
                case 2:
                    showCustomersByProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                default:
                    System.out.println("invalid command");
            }
        }
    }

    private static void deleteCustomer() {
        System.out.println("enter customer id");
        customerDAO.deleteCustomer(Long.parseLong(scanner.nextLine()));
    }

    private static void deleteProduct() {
        System.out.println("enter product id");
        productDao.deleteProduct(Long.parseLong(scanner.nextLine()));
    }

    private static void showCustomersByProduct() {
        System.out.println("enter product id");
        List<String> customersByProduct = productDao.getProductCustomers(Long.parseLong(scanner.nextLine()));
        if (!customersByProduct.isEmpty()) {
            customersByProduct.forEach(System.out::println);
        }else {
            System.out.println("No customers found");
        }
    }

    private static void showProductsByCustomers() {
        System.out.println("enter customer id");
        List<String> productsByCustomers = customerDAO.getCustomerProducts(Long.parseLong(scanner.nextLine()));
        if (!productsByCustomers.isEmpty()) {
            productsByCustomers.forEach(System.out::println);
        }else {
            System.out.println("No products found");
        }
    }

    private static void dbInit() {
        try(Session session = JavaBasedSessionFactory.getSessionFactory().getCurrentSession()){
        }
    }
}
