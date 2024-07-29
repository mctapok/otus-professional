package ru.otus.gavrilov.spring.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.gavrilov.spring.context.service.Cart;

import java.util.Scanner;

@ComponentScan
public class App {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Cart cart = context.getBean(Cart.class);
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
