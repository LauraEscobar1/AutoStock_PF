package com.autostock.dto;

import jakarta.validation.constraints.Email;
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
public class ProveedorDTO {

    private Long id;

    @NotBlank(message = "El nombre del proveedor es obligatorio")
    @Size(max = 100, message = "El nombre del proveedor no puede superar los 100 caracteres")
    private String nombre;

    @NotBlank(message = "El telefono del proveedor es obligatorio")
    @Size(max = 20, message = "El telefono del proveedor no puede superar los 20 caracteres")
    private String telefono;

    @NotBlank(message = "El correo del proveedor es obligatorio")
    @Email(message = "El correo del proveedor debe tener un formato valido")
    @Size(max = 100, message = "El correo del proveedor no puede superar los 100 caracteres")
    private String correo;
}
