package ru.otus.denis.spring_boot.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProductDto {
    private String title;
    private Long price;
}
