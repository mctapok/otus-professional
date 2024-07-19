package ru.gavrilov.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PetitionDto {
    private final String content;

    @JsonCreator
    public PetitionDto(@JsonProperty("content") String content) {
        this.content = content;
    }
}
