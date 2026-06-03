package com.autostock.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para sucursales")
public class SucursalDTO {

    @Schema(description = "Nombre")
    @NotBlank(message = "El nombre de la sucursal es obligatorio")
    private String nombre;

    @Schema(description = "Ubicacion")
    @NotBlank(message = "La ubicacion de la sucursal es obligatoria")
    private String ubicacion;

    public SucursalDTO() {
    }

    public SucursalDTO(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}

