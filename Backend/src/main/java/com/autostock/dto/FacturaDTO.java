package com.autostock.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import com.autostock.model.TipoFactura;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Schema(description = "DTO para facturas")
public class FacturaDTO {

    @Schema(description = "Identificador unico")
    private Long id;

    @Schema(description = "Tipo")
    @NotNull(message = "El tipo de factura es obligatorio")
    private TipoFactura tipo;

    @Schema(description = "Fecha")
    private LocalDate fecha;

    @Schema(description = "Total")
    @Positive(message = "El total debe ser mayor que cero")
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

