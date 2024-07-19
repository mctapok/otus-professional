package ru.gavrilov.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("petitions")
public class Petition {
    @Id
    private final Long id;
    private final String content;
//    @Column("date_created")
    private final LocalDateTime dateCreated;
//    @Column("client_id")
    private Long clientId;

    public Petition(Long id, String content, LocalDateTime dateCreated) {
        this.id = id;
        this.content = content;
        this.dateCreated = dateCreated;
    }

    public void setClientId(Client client){
        this.clientId = client.getId();
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
}
