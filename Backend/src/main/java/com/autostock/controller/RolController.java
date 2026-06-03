package com.autostock.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.autostock.dto.RolDTO;
import com.autostock.model.Rol;
import com.autostock.service.RolService;
import jakarta.validation.Valid;
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
@Tag(name = "Roles", description = "Operaciones para la gestion de roles")
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @Operation(summary = "Crear rol", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @PostMapping
    public ResponseEntity<Rol> crearRol(@Valid @RequestBody RolDTO rolDTO) {
        Rol rolCreado = rolService.crearRol(rolDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolCreado);
    }

    @Operation(summary = "Listar registros", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @GetMapping
    public ResponseEntity<List<Rol>> obtenerTodos() {
        return ResponseEntity.ok(rolService.obtenerTodos());
    }

    @Operation(summary = "Buscar por id", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(rolService.obtenerPorId(id));
    }

    @Operation(summary = "Actualizar rol", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizarRol(
            @PathVariable Long id,
            @Valid @RequestBody RolDTO rolDTO) {
        return ResponseEntity.ok(rolService.actualizarRol(id, rolDTO));
    }

    @Operation(summary = "Eliminar rol", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        rolService.eliminarRol(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> manejarNoEncontrado(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}

