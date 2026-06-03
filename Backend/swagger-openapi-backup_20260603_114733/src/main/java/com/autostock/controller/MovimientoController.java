package com.autostock.controller;

import com.autostock.dto.MovimientoDTO;
import com.autostock.service.MovimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Movimientos", description = "Operaciones para la gestion de movimientos de inventario")
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    @Operation(summary = "Listar movimientos", responses = {
            @ApiResponse(responseCode = "200", description = "Movimientos listados correctamente")
    })
    public ResponseEntity<List<MovimientoDTO>> listarTodos() {
        return ResponseEntity.ok(movimientoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar movimiento por id", responses = {
            @ApiResponse(responseCode = "200", description = "Movimiento encontrado"),
            @ApiResponse(responseCode = "404", description = "Movimiento no encontrado")
    })
    public ResponseEntity<MovimientoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(movimientoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear movimiento", responses = {
            @ApiResponse(responseCode = "201", description = "Movimiento creado correctamente")
    })
    public ResponseEntity<MovimientoDTO> guardar(@RequestBody MovimientoDTO dto) {
        MovimientoDTO movimientoGuardado = movimientoService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoGuardado);
    }

    @PostMapping("/entrada")
    @Operation(summary = "Registrar entrada de inventario", responses = {
            @ApiResponse(responseCode = "201", description = "Entrada registrada correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto o usuario no encontrado")
    })
    public ResponseEntity<MovimientoDTO> registrarEntrada(@RequestBody MovimientoDTO dto) {
        MovimientoDTO movimientoGuardado = movimientoService.registrarEntrada(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoGuardado);
    }

    @PostMapping("/salida")
    @Operation(summary = "Registrar salida de inventario", responses = {
            @ApiResponse(responseCode = "201", description = "Salida registrada correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto o usuario no encontrado")
    })
    public ResponseEntity<MovimientoDTO> registrarSalida(@RequestBody MovimientoDTO dto) {
        MovimientoDTO movimientoGuardado = movimientoService.registrarSalida(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoGuardado);
    }

    @PostMapping("/devolucion")
    @Operation(summary = "Registrar devolucion de inventario", responses = {
            @ApiResponse(responseCode = "201", description = "Devolucion registrada correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto o usuario no encontrado")
    })
    public ResponseEntity<MovimientoDTO> registrarDevolucion(@RequestBody MovimientoDTO dto) {
        MovimientoDTO movimientoGuardado = movimientoService.registrarDevolucion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoGuardado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar movimiento", responses = {
            @ApiResponse(responseCode = "200", description = "Movimiento actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Movimiento no encontrado")
    })
    public ResponseEntity<MovimientoDTO> actualizar(@PathVariable Long id,
            @RequestBody MovimientoDTO dto) {
        return ResponseEntity.ok(movimientoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar movimiento", responses = {
            @ApiResponse(responseCode = "204", description = "Movimiento eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Movimiento no encontrado")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        movimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> manejarNoEncontrado(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
