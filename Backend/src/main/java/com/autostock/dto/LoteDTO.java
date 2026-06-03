package com.autostock.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Schema(description = "DTO para lotes")
public class LoteDTO {

    @Schema(description = "Identificador unico")
    private Long id;

    @Schema(description = "Numero")
    @NotBlank(message = "El numero del lote es obligatorio")
    private String numero;

    @Schema(description = "Fecha de vencimiento")
    @NotNull(message = "La fecha de vencimiento es obligatoria")
    private LocalDate fechaVencimiento;

    @Schema(description = "Identificador del producto")
    @NotNull(message = "El producto del lote es obligatorio")
    private Long productoId;

    public LoteDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }
}

