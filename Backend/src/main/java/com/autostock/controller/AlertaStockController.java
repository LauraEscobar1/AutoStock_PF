package com.autostock.controller;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.autostock.dto.AlertaStockDTO;
import com.autostock.service.AlertaStockService;
import java.util.List;
import java.util.NoSuchElementException;
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
@Tag(name = "Alertas de Stock", description = "Operaciones para la gestion de alertas de stock")
@RequestMapping("/api/alertas-stock")
public class AlertaStockController {

    private final AlertaStockService alertaStockService;

    public AlertaStockController(AlertaStockService alertaStockService) {
        this.alertaStockService = alertaStockService;
    }

    @Operation(summary = "Listar registros", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @GetMapping
    public ResponseEntity<List<AlertaStockDTO>> listarTodos() {
        return ResponseEntity.ok(alertaStockService.listarTodos());
    }

    @Operation(summary = "Buscar por id", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @GetMapping("/{id}")
    public ResponseEntity<AlertaStockDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alertaStockService.buscarPorId(id));
    }

    @Operation(summary = "Crear registro", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @PostMapping
    public ResponseEntity<AlertaStockDTO> guardar(@Valid @RequestBody AlertaStockDTO dto) {
        AlertaStockDTO alertaStockGuardada = alertaStockService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(alertaStockGuardada);
    }

    @Operation(summary = "Actualizar registro", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @PutMapping("/{id}")
    public ResponseEntity<AlertaStockDTO> actualizar(@PathVariable Long id,
            @Valid @RequestBody AlertaStockDTO dto) {
        return ResponseEntity.ok(alertaStockService.actualizar(id, dto));
    }

    @Operation(summary = "Eliminar registro", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        alertaStockService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> manejarNoEncontrado(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}

