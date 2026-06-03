package com.autostock.controller;

import com.autostock.dto.FacturaDTO;
import com.autostock.model.Factura;
import com.autostock.service.FacturaService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaService facturaService;

    @PostMapping
    public ResponseEntity<FacturaDTO> crearFactura(@RequestBody FacturaDTO facturaDTO) {
        Factura factura = toEntity(facturaDTO);
        Factura facturaCreada = facturaService.crearFactura(factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(facturaCreada));
    }

    @GetMapping
    public ResponseEntity<List<FacturaDTO>> listarFacturas() {
        List<FacturaDTO> facturas = facturaService.listarFacturas()
                .stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> obtenerFactura(@PathVariable Long id) {
        return ResponseEntity.ok(toDTO(facturaService.obtenerFactura(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturaDTO> actualizarFactura(
            @PathVariable Long id,
            @RequestBody FacturaDTO facturaDTO) {
        Factura factura = toEntity(facturaDTO);
        Factura facturaActualizada = facturaService.actualizarFactura(id, factura);
        return ResponseEntity.ok(toDTO(facturaActualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Long id) {
        facturaService.eliminarFactura(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> manejarNoEncontrado(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    private Factura toEntity(FacturaDTO dto) {
        Factura factura = new Factura();
        factura.setId(dto.getId());
        factura.setTipo(dto.getTipo());
        factura.setFecha(dto.getFecha());
        factura.setTotal(dto.getTotal());
        return factura;
    }

    private FacturaDTO toDTO(Factura factura) {
        FacturaDTO dto = new FacturaDTO();
        dto.setId(factura.getId());
        dto.setTipo(factura.getTipo());
        dto.setFecha(factura.getFecha());
        dto.setTotal(factura.getTotal());
        return dto;
    }
}
