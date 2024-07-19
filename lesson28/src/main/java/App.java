import entities.Address;
import entities.Client;
import entities.Phone;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class App {
    private static final Logger LOGGER = LogManager.getLogger("console");

    public static void main(String[] args) {
//        Client client = new Client("Petr");
//        Address address = new Address("some street");
//        Phone phone = new Phone();
//        Phone phone1 = new Phone();
//
//        phone.setNumber("+988924324884");
//        phone1.setNumber("+987417718823");
//        phone.setClient(client);
//        phone1.setClient(client);
//
//        client.setAddress(address);
//        client.getPhoneNumber().add(phone);
//        client.getPhoneNumber().add(phone1);

        selectQueryWhere();
//        selectQuery();
    }

    public static Client persistCLient(Client client){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SingleUnit");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(client);
        Client addedClient = entityManager.find(Client.class, client.getId());

        transaction.commit();
        entityManager.close();

        return addedClient;
    }
    public static Client selectQueryWhere() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SingleUnit");
        EntityManager entityManager = emf.createEntityManager();

        Client client = entityManager.createQuery("select C from Client C where C.name =:name", Client.class)
                .setParameter("name", "Petr")
                .getSingleResult();
        entityManager.close();
        LOGGER.info("select query where, result name: {} id: {}", client.getName(), client.getId());

        return client;
    }

    public static List<Client> selectQuery() {
        LOGGER.info("select query");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SingleUnit");
        EntityManager entityManager = emf.createEntityManager();
        List<Client> listOfClients = entityManager.createQuery("select C from Client C left join fetch C.phoneNumber", Client.class)
                .getResultList();
        entityManager.close();
        return listOfClients;
    }
}

