package ru.gavrilov.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientDto {

    private String name;

    public String getName() {
        return name;
    }

    public ClientDto(){

    }

    public ClientDto(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
