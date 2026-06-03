package com.autostock.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "alertas_stock")
public class AlertaStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer nivelActual;

    private Integer nivelMinimo;

    private LocalDate fecha;

    public AlertaStock() {
    }

    public void generarAlerta() {
        if (nivelActual != null && nivelMinimo != null && nivelActual <= nivelMinimo) {
            System.out.println("Alerta de stock generada");
        }
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

    @Override
    public String toString() {
        return "AlertaStock{"
                + "id=" + id
                + ", nivelActual=" + nivelActual
                + ", nivelMinimo=" + nivelMinimo
                + ", fecha=" + fecha
                + '}';
    }
}
