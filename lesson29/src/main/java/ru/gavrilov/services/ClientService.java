package ru.gavrilov.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gavrilov.dto.ClientDto;
import ru.gavrilov.dto.PetitionDto;
import ru.gavrilov.entities.Client;
import org.springframework.stereotype.Service;
import ru.gavrilov.repositories.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class.getName());

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public Client createClient(ClientDto clientDto) {
        Client client = new Client(null, clientDto.getName(), Set.of());
        Client savedClient = clientRepository.save(client);
        LOGGER.info("created client {}", savedClient);
        return clientRepository.save(client);
    }

    public Optional<Client> findById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        LOGGER.info("client found: {}", client);
        return client;
    }

    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>(clientRepository.findAll());
        LOGGER.info("clients found: {}", clients);
        return clients;
    }

    public Client createPetition(PetitionDto petitionDto, Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.addPetition(petitionDto);
            return clientRepository.save(client);
        }
        throw new RuntimeException("Client not found");
    }

    public ClientDto deleteById(Long id) {
        ClientDto clientDto = new ClientDto(findById(id).orElseThrow(() -> new RuntimeException("client not found")).getName());
        clientRepository.deleteById(id);
        LOGGER.info("deleted client: id:{}, {}", id, clientDto);
        return clientDto;
    }

    public void updateById(Long id, ClientDto clientDto) {
        Client updatingClient = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("client not found"));
        LOGGER.info("client: {} before update", updatingClient);
        updatingClient.setName(clientDto.getName());
        LOGGER.info("client: {} after update", updatingClient);
        clientRepository.save(updatingClient);
    }
}
