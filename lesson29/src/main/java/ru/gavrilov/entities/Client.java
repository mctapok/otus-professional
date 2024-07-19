package ru.gavrilov.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import ru.gavrilov.dto.PetitionDto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table("clients")
public class Client {
    @Id
    private Long id;
    private String name;

    @MappedCollection(idColumn = "client_id")
    private Set<Petition> petitions = new HashSet<>();

    public void addPetition(PetitionDto petitionDto){
        Petition petition = new Petition(null, petitionDto.getContent(), LocalDateTime.now());
        petitions.add(petition);
        petition.setClientId(this);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", petitions=" + petitions +
                '}';
    }
}
