package com.autostock.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.autostock.dto.ProveedorDTO;
import com.autostock.service.ProveedorService;
import jakarta.validation.Valid;
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
@Tag(name = "Proveedores", description = "Operaciones para la gestion de proveedores")
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @Operation(summary = "Crear registro", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @PostMapping
    public ResponseEntity<ProveedorDTO> crear(@Valid @RequestBody ProveedorDTO proveedorDTO) {
        ProveedorDTO proveedorCreado = proveedorService.crear(proveedorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorCreado);
    }

    @Operation(summary = "Listar registros", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> listar() {
        return ResponseEntity.ok(proveedorService.listar());
    }

    @Operation(summary = "Buscar por id", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.obtenerPorId(id));
    }

    @Operation(summary = "Actualizar registro", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProveedorDTO proveedorDTO) {
        return ResponseEntity.ok(proveedorService.actualizar(id, proveedorDTO));
    }

    @Operation(summary = "Eliminar registro", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> manejarNoEncontrado(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}

