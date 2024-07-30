package ru.gavrilov.hibernate.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.gavrilov.hibernate.entities.Customer;
import ru.gavrilov.hibernate.entities.Product;
import ru.gavrilov.hibernate.entities.Purchase;

import java.lang.invoke.MethodHandles;
import java.util.Properties;

public final class JavaBasedSessionFactory {
    private final static Log LOGGER = LogFactory.getLog(MethodHandles.lookup().lookupClass());

    public static SessionFactory getSessionFactory(){
        try{
            Configuration configuration = getConfiguration();
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(Purchase.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            LOGGER.info("Hibernate Java Config serviceRegistry created");

            return configuration.buildSessionFactory(serviceRegistry);
        }catch (Throwable e){
            LOGGER.error("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        Properties props = new Properties();

        props.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        props.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/hibernate");
        props.put("hibernate.connection.username", "postgres");
        props.put("hibernate.connection.password", "postgres");
        props.put("hibernate.default_schema", "lesson_26");
        props.put("hibernate.current_session_context_class", "thread");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.show_sql", "true");
//        props.put("hibernate.hbm2ddl.import_files", "sql/hw26script.sql");

        configuration.setProperties(props);
        return configuration;
    }
}