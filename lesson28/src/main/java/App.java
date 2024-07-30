import entities.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class App {
    private static final Logger LOGGER = LogManager.getLogger("console");
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("SingleUnit");

    public static void main(String[] args) {

        selectQueryWhere();
        selectQuery();
    }

    public static Client persistCLient(Client client) {
        EntityManager entityManager = getEntityMgrFromFactory();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(client);
            Client addedClient = entityManager.find(Client.class, client.getId());
            transaction.commit();
            return addedClient;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.error("transaction failed {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public static Client selectQueryWhere() {
        EntityManager entityManager = getEntityMgrFromFactory();

        Client client = entityManager.createQuery("select C from Client C where C.name =:name", Client.class)
                .setParameter("name", "Petr")
                .getSingleResult();
        entityManager.close();
        LOGGER.info("select query where, result name: {} id: {}", client.getName(), client.getId());

        return client;
    }

    public static List<Client> selectQuery() {
        LOGGER.info("select query");
        EntityManager entityManager = getEntityMgrFromFactory();
        List<Client> listOfClients = entityManager.createQuery("select C from Client C left join fetch C.phoneNumber", Client.class)
                .getResultList();
        entityManager.close();
        return listOfClients;
    }

    private static EntityManager getEntityMgrFromFactory() {
        return emf.createEntityManager();
    }
}

