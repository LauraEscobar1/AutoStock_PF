package com.autostock.dto;

import com.autostock.model.TipoFactura;
import java.time.LocalDate;

public class FacturaDTO {

    private Long id;

    private TipoFactura tipo;

    private LocalDate fecha;

    private Double total;

    public FacturaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoFactura getTipo() {
        return tipo;
    }

    public void setTipo(TipoFactura tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "FacturaDTO{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", fecha=" + fecha +
                ", total=" + total +
                '}';
    }
}
