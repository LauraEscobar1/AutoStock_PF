package com.autostock.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.autostock.dto.CategoriaDTO;
import com.autostock.service.CategoriaService;
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
@Tag(name = "Categorias", description = "Operaciones para la gestion de categorias")
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Operation(summary = "Crear registro", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @PostMapping
    public ResponseEntity<CategoriaDTO> crear(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaCreada = categoriaService.crear(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCreada);
    }

    @Operation(summary = "Listar registros", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listar() {
        return ResponseEntity.ok(categoriaService.listar());
    }

    @Operation(summary = "Buscar por id", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.obtenerPorId(id));
    }

    @Operation(summary = "Actualizar registro", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.ok(categoriaService.actualizar(id, categoriaDTO));
    }

    @Operation(summary = "Eliminar registro", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> manejarNoEncontrado(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}

