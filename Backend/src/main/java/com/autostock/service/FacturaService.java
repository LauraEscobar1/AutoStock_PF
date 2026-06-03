package com.autostock.service;

import com.autostock.model.Factura;
import java.util.List;

public interface FacturaService {

    Factura crearFactura(Factura factura);

    Factura generarFacturaCompra(Factura factura);

    Factura generarFacturaVenta(Factura factura);

    Factura obtenerFactura(Long id);

    List<Factura> listarFacturas();

    Factura actualizarFactura(Long id, Factura factura);

    void eliminarFactura(Long id);
}
