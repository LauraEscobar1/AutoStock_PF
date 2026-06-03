package com.autostock.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "pedidos_automaticos")
public class PedidoAutomatico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    private LocalDate fechaGenerado;

    public PedidoAutomatico() {
    }

    public void generarPedido() {
        System.out.println("Se genero un pedido automatico");
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

    @Override
    public String toString() {
        return "PedidoAutomatico{"
                + "id=" + id
                + ", cantidad=" + cantidad
                + ", fechaGenerado=" + fechaGenerado
                + '}';
    }
}
