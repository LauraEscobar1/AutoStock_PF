package com.autostock.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Schema(description = "DTO para alertas de stock")
public class AlertaStockDTO {

    @Schema(description = "Identificador unico")
    private Long id;

    @Schema(description = "Nivel actual")
    @NotNull(message = "El nivel actual es obligatorio")
    @PositiveOrZero(message = "El nivel actual debe ser mayor o igual a cero")
    private Integer nivelActual;

    @Schema(description = "Nivel minimo")
    @NotNull(message = "El nivel minimo es obligatorio")
    @PositiveOrZero(message = "El nivel minimo debe ser mayor o igual a cero")
    private Integer nivelMinimo;

    @Schema(description = "Fecha")
    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    public AlertaStockDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNivelActual() {
        return nivelActual;
    }

    public void setNivelActual(Integer nivelActual) {
        this.nivelActual = nivelActual;
    }

    public Integer getNivelMinimo() {
        return nivelMinimo;
    }

    public void setNivelMinimo(Integer nivelMinimo) {
        this.nivelMinimo = nivelMinimo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}

