package com.autostock.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import com.autostock.model.EstadoProducto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "DTO para productos")
public class ProductoDTO {

    @Schema(description = "Nombre")
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;

    @Schema(description = "Codigo")
    @NotBlank(message = "El codigo del producto es obligatorio")
    private String codigo;

    @Schema(description = "Descripcion")
    private String descripcion;

    @Schema(description = "Cantidad")
    @PositiveOrZero(message = "La cantidad debe ser mayor o igual a cero")
    private Integer cantidad;

    @Schema(description = "Precio unitario")
    @Positive(message = "El precio unitario debe ser mayor que cero")
    private Double precioUnitario;

    @Schema(description = "Ubicacion")

    private String ubicacion;

    @Schema(description = "Estado")
    @NotNull(message = "El estado del producto es obligatorio")
    private EstadoProducto estado;

    @Schema(description = "Identificador de la categoria")
    @NotNull(message = "La categoria del producto es obligatoria")
    private Long categoriaId;

    @Schema(description = "Identificador del proveedor")
    @NotNull(message = "El proveedor del producto es obligatorio")
    private Long proveedorId;

    public ProductoDTO() {
    }

    public ProductoDTO(String nombre, String codigo, String descripcion, Integer cantidad,
            Double precioUnitario, String ubicacion, EstadoProducto estado, Long categoriaId,
            Long proveedorId) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.categoriaId = categoriaId;
        this.proveedorId = proveedorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public EstadoProducto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }
}

