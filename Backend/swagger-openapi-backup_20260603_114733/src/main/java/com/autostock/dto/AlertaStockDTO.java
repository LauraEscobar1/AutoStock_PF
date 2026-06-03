package com.autostock.dto;

import java.time.LocalDate;

public class AlertaStockDTO {

    private Long id;

    private Integer nivelActual;

    private Integer nivelMinimo;

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
