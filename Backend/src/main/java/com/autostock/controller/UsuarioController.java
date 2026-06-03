package com.autostock.controller;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.autostock.dto.UsuarioDTO;
import com.autostock.service.UsuarioService;
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
@Tag(name = "Usuarios", description = "Operaciones para la gestion de usuarios")
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Listar registros", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @Operation(summary = "Buscar por id", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @Operation(summary = "Crear registro", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @PostMapping
    public ResponseEntity<UsuarioDTO> guardar(@Valid @RequestBody UsuarioDTO dto) {
        UsuarioDTO usuarioGuardado = usuarioService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
    }

    @Operation(summary = "Actualizar registro", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.actualizar(id, dto));
    }

    @Operation(summary = "Eliminar registro", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> manejarNoEncontrado(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}

