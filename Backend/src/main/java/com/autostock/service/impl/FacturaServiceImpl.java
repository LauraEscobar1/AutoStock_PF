package com.autostock.service.impl;

import com.autostock.model.Factura;
import com.autostock.repository.FacturaRepository;
import com.autostock.service.FacturaService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;

    @Override
    public Factura crearFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Factura> listarFacturas() {
        return facturaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Factura obtenerFactura(Long id) {
        return buscarFactura(id);
    }

    @Override
    public Factura actualizarFactura(Long id, Factura factura) {
        Factura facturaExistente = buscarFactura(id);
        facturaExistente.setTipo(factura.getTipo());
        facturaExistente.setFecha(factura.getFecha());
        facturaExistente.setTotal(factura.getTotal());
        return facturaRepository.save(facturaExistente);
    }

    @Override
    public void eliminarFactura(Long id) {
        Factura factura = buscarFactura(id);
        facturaRepository.delete(factura);
    }

    private Factura buscarFactura(Long id) {
        return facturaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Factura no encontrada con id: " + id));
    }
}
