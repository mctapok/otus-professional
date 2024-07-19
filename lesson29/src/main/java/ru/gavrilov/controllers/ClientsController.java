package ru.gavrilov.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gavrilov.dto.ClientDto;
import ru.gavrilov.dto.PetitionDto;
import ru.gavrilov.entities.Client;
import lombok.RequiredArgsConstructor;
import ru.gavrilov.services.ClientService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v0/clients")
public class ClientsController {
    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody ClientDto clientDto){
        return clientService.createClient(clientDto);
    }

    @GetMapping("/{id}")
    public Client findClientById(@PathVariable Long id){
        return clientService.findById(id).orElseThrow(() -> new RuntimeException("Client with id " + id + " not found"));
    }

    @GetMapping
    public List<Client> findAllClients(){
        return clientService.findAll();
    }

    @DeleteMapping("delete/{id}")
    public ClientDto deleteClientById(@PathVariable Long id){
        return clientService.deleteById(id);
    }

    @PatchMapping("{id}/update")
    public void updateClientById(@PathVariable Long id, @RequestBody ClientDto clientDto){
        clientService.updateById(id, clientDto);
    }

    @PostMapping("{id}/create_petition")
    @ResponseStatus(HttpStatus.CREATED)
    public Client createPetition(@RequestBody PetitionDto petitionDto, @PathVariable Long id){
        return clientService.createPetition(petitionDto, id);
    }

}
