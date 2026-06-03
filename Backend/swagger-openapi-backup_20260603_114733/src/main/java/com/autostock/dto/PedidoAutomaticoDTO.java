package com.autostock.dto;

import java.time.LocalDate;

public class PedidoAutomaticoDTO {

    private Long id;

    private Integer cantidad;

    private LocalDate fechaGenerado;

    public PedidoAutomaticoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaGenerado() {
        return fechaGenerado;
    }

    public void setFechaGenerado(LocalDate fechaGenerado) {
        this.fechaGenerado = fechaGenerado;
    }
}
