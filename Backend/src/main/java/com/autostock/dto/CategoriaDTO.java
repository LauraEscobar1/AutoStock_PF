package com.autostock.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDTO {

    private Long id;

    @NotBlank(message = "El nombre de la categoria es obligatorio")
    @Size(max = 100, message = "El nombre de la categoria no puede superar los 100 caracteres")
    private String nombre;
}
