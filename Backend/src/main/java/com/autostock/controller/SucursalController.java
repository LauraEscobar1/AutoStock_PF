package com.autostock.controller;

import com.autostock.dto.SucursalDTO;
import com.autostock.model.Sucursal;
import com.autostock.service.SucursalService;
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
@RequestMapping("/api/sucursales")
public class SucursalController {

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @PostMapping
    public ResponseEntity<Sucursal> crearSucursal(@RequestBody SucursalDTO dto) {
        Sucursal sucursalCreada = sucursalService.crearSucursal(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalCreada);
    }

    @GetMapping
    public ResponseEntity<List<Sucursal>> obtenerTodas() {
        return ResponseEntity.ok(sucursalService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(sucursalService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> actualizarSucursal(
            @PathVariable Long id,
            @RequestBody SucursalDTO dto) {
        return ResponseEntity.ok(sucursalService.actualizarSucursal(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Long id) {
        sucursalService.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> manejarNoEncontrado(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
