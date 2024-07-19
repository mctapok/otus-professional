package ru.gavrilov.repositories;

import org.springframework.stereotype.Repository;
import ru.gavrilov.entities.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    @Override
    List<Client> findAll();
}
