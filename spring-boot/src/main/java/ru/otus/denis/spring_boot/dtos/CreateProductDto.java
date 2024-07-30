package ru.otus.denis.spring_boot.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProductDto {
    private Long price;
}
