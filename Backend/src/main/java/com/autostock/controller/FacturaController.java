package com.autostock.controller;

import jakarta.validation.Valid;

import com.autostock.dto.FacturaDTO;
import com.autostock.model.Factura;
import com.autostock.service.FacturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Facturas", description = "Operaciones para la gestion de facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaService facturaService;

    @PostMapping
    @Operation(summary = "Crear factura", responses = {
            @ApiResponse(responseCode = "201", description = "Factura creada correctamente")
    })
    public ResponseEntity<FacturaDTO> crearFactura(@Valid @RequestBody FacturaDTO facturaDTO) {
        Factura factura = toEntity(facturaDTO);
        Factura facturaCreada = facturaService.crearFactura(factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(facturaCreada));
    }

    @PostMapping("/compra")
    @Operation(summary = "Generar factura de compra", responses = {
            @ApiResponse(responseCode = "201", description = "Factura de compra generada correctamente")
    })
    public ResponseEntity<FacturaDTO> generarFacturaCompra(@Valid @RequestBody FacturaDTO facturaDTO) {
        Factura factura = toEntity(facturaDTO);
        Factura facturaCreada = facturaService.generarFacturaCompra(factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(facturaCreada));
    }

    @PostMapping("/venta")
    @Operation(summary = "Generar factura de venta", responses = {
            @ApiResponse(responseCode = "201", description = "Factura de venta generada correctamente")
    })
    public ResponseEntity<FacturaDTO> generarFacturaVenta(@Valid @RequestBody FacturaDTO facturaDTO) {
        Factura factura = toEntity(facturaDTO);
        Factura facturaCreada = facturaService.generarFacturaVenta(factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(facturaCreada));
    }

    @GetMapping
    @Operation(summary = "Listar facturas", responses = {
            @ApiResponse(responseCode = "200", description = "Facturas listadas correctamente")
    })
    public ResponseEntity<List<FacturaDTO>> listarFacturas() {
        List<FacturaDTO> facturas = facturaService.listarFacturas()
                .stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar factura por id", responses = {
            @ApiResponse(responseCode = "200", description = "Factura encontrada"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    public ResponseEntity<FacturaDTO> obtenerFactura(@PathVariable Long id) {
        return ResponseEntity.ok(toDTO(facturaService.obtenerFactura(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar factura", responses = {
            @ApiResponse(responseCode = "200", description = "Factura actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    public ResponseEntity<FacturaDTO> actualizarFactura(
            @PathVariable Long id,
            @Valid @RequestBody FacturaDTO facturaDTO) {
        Factura factura = toEntity(facturaDTO);
        Factura facturaActualizada = facturaService.actualizarFactura(id, factura);
        return ResponseEntity.ok(toDTO(facturaActualizada));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar factura", responses = {
            @ApiResponse(responseCode = "204", description = "Factura eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
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

