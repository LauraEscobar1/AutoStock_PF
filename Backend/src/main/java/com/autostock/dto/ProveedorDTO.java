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
@Schema(description = "DTO para proveedores")
public class ProveedorDTO {

    @Schema(description = "Identificador unico")
    private Long id;

    @NotBlank(message = "El nombre del proveedor es obligatorio")
    @Size(max = 100, message = "El nombre del proveedor no puede superar los 100 caracteres")
    @Schema(description = "Nombre")
    private String nombre;

    @Size(max = 100, message = "El contacto del proveedor no puede superar los 100 caracteres")
    @Schema(description = "Contacto")
    @NotBlank(message = "El contacto del proveedor es obligatorio")
    private String contacto;

    @Schema(description = "Descuentos")

    private Double descuentos;
}

