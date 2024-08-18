package ru.otus.denis.spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
Создайте сервис, который хранит продукты (id, title, price)
Сервис должен быть разделен на стандартный набор слоев: контроллеры, сервисы, репозитории (в данном задании репозитории в проекте не используют в качестве источника данных базу данных)
Хранение продуктов орагнизуйте через List в отдельном компоненте
Сервис должен давать возможность: запросить все продукты, запросить продукт по id, создавть новый продукт
*/
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
