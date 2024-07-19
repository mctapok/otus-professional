package ru.otus.gavrilov.spring.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

/*Имеется класс Product (id, название, цена).
        Товары хранятся в бине ProductRepository, в виде List,
        при старте приложения в него необходимо добавить 10 любых товаров.
         ProductRepository должен позволять получить весь список или один товар по id.
          Создаем бин Cart, в который можно добавлять и удалять товары
           по id из репозитория.
            Написать консольное приложение, позволяющее управлять корзиной.
             При каждом запросе корзины из контекста, должна создаваться новая корзина.*/
@ComponentScan
public class App {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        Cart cart = context.getBean(Cart.class);
//        Cart cart2 = context.getBean(Cart.class);
//        ProductRepositoryImpl repository = new ProductRepositoryImpl();
//        repository.allProducts();
        Scanner scanner = new Scanner(System.in);
        while (true) {
//            Cart cart = context.getBean(Cart.class); //создается новая корзина
            System.out.println("""
                    выберите действие
                    1: добавить товар в корзину
                    2: удалить товар из корзины
                    """);
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    System.out.println("введите id продукта");
                    cart.addProduct(Long.parseLong(scanner.nextLine()));
                    System.out.println(cart.showProductsInCart());
                    break;
                case "2":
                    System.out.println("введите id продукта");
                    cart.removeProduct(Long.parseLong(scanner.nextLine()));
                    System.out.println(cart.showProductsInCart());
                    break;
                default:
                    System.out.println("invalid command");
                    break;
            }
        }
    }
}
