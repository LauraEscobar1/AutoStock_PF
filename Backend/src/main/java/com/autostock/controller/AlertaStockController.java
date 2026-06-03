package com.autostock.controller;

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
@RequestMapping("/api/alertas-stock")
public class AlertaStockController {

    private final AlertaStockService alertaStockService;

    public AlertaStockController(AlertaStockService alertaStockService) {
        this.alertaStockService = alertaStockService;
    }

    @GetMapping
    public ResponseEntity<List<AlertaStockDTO>> listarTodos() {
        return ResponseEntity.ok(alertaStockService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaStockDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alertaStockService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AlertaStockDTO> guardar(@RequestBody AlertaStockDTO dto) {
        AlertaStockDTO alertaStockGuardada = alertaStockService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(alertaStockGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertaStockDTO> actualizar(@PathVariable Long id,
            @RequestBody AlertaStockDTO dto) {
        return ResponseEntity.ok(alertaStockService.actualizar(id, dto));
    }

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
