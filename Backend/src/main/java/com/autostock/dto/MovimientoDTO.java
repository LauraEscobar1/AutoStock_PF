package com.autostock.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import com.autostock.model.TipoMovimiento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

@Schema(description = "DTO para movimientos")
public class MovimientoDTO {

    @Schema(description = "Identificador unico")
    private Long id;

    @Schema(description = "Tipo")
    @NotNull(message = "El tipo de movimiento es obligatorio")
    private TipoMovimiento tipo;

    @Schema(description = "Fecha y hora")
    private LocalDateTime fechaHora;

    @Schema(description = "Cantidad")
    @NotNull(message = "La cantidad del movimiento es obligatoria")
    @Positive(message = "La cantidad del movimiento debe ser mayor que cero")
    private Integer cantidad;

    @Schema(description = "Identificador del producto")
    @NotNull(message = "El producto del movimiento es obligatorio")
    private Long productoId;

    @Schema(description = "Identificador del usuario")
    @NotNull(message = "El usuario del movimiento es obligatorio")
    private Long usuarioId;

    public MovimientoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimiento tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}

