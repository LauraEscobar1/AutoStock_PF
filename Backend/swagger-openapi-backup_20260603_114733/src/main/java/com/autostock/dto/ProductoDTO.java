package com.autostock.dto;

import com.autostock.model.EstadoProducto;

public class ProductoDTO {

    private String nombre;

    private String codigo;

    private String descripcion;

    private Integer cantidad;

    private Double precioUnitario;

    private String ubicacion;

    private EstadoProducto estado;

    private Long categoriaId;

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
