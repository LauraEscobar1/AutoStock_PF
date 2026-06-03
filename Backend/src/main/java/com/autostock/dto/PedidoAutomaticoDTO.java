package com.autostock.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Schema(description = "DTO para pedidos automaticos")
public class PedidoAutomaticoDTO {

    @Schema(description = "Identificador unico")
    private Long id;

    @Schema(description = "Cantidad")
    @NotNull(message = "La cantidad del pedido automatico es obligatoria")
    @Positive(message = "La cantidad del pedido automatico debe ser mayor que cero")
    private Integer cantidad;

    @Schema(description = "Fecha de generacion")
    @NotNull(message = "La fecha de generacion es obligatoria")
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

