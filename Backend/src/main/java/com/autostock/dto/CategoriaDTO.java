package com.autostock.dto;

import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(description = "DTO para categorias")
public class CategoriaDTO {

    @Schema(description = "Identificador unico")
    private Long id;

    @NotBlank(message = "El nombre de la categoria es obligatorio")
    @Size(max = 100, message = "El nombre de la categoria no puede superar los 100 caracteres")
    @Schema(description = "Nombre")
    private String nombre;

    @Size(max = 100, message = "El tipo de la categoria no puede superar los 100 caracteres")
    @Schema(description = "Tipo")
    private String tipo;

    @Size(max = 100, message = "El tamano de la categoria no puede superar los 100 caracteres")
    @Schema(description = "Tamano")
    private String tamano;
}

