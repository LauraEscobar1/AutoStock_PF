package com.autostock.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para usuarios")
public class UsuarioDTO {

    @Schema(description = "Identificador unico")
    private Long id;

    @Schema(description = "Nombre de usuario")
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String nombreUsuario;

    @Schema(description = "Contrasena")
    private String contrasena;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nombreUsuario, String contrasena) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

