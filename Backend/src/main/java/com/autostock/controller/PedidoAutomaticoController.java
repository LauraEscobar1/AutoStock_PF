package com.autostock.controller;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.autostock.dto.PedidoAutomaticoDTO;
import com.autostock.service.PedidoAutomaticoService;
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
@Tag(name = "Pedidos Automaticos", description = "Operaciones para la gestion de pedidos automaticos")
@RequestMapping("/api/pedidos-automaticos")
public class PedidoAutomaticoController {

    private final PedidoAutomaticoService pedidoAutomaticoService;

    public PedidoAutomaticoController(PedidoAutomaticoService pedidoAutomaticoService) {
        this.pedidoAutomaticoService = pedidoAutomaticoService;
    }

    @Operation(summary = "Listar registros", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @GetMapping
    public ResponseEntity<List<PedidoAutomaticoDTO>> listarTodos() {
        return ResponseEntity.ok(pedidoAutomaticoService.listarTodos());
    }

    @Operation(summary = "Buscar por id", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @GetMapping("/{id}")
    public ResponseEntity<PedidoAutomaticoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoAutomaticoService.buscarPorId(id));
    }

    @Operation(summary = "Crear registro", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @PostMapping
    public ResponseEntity<PedidoAutomaticoDTO> guardar(@Valid @RequestBody PedidoAutomaticoDTO dto) {
        PedidoAutomaticoDTO pedidoAutomaticoGuardado = pedidoAutomaticoService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoAutomaticoGuardado);
    }

    @Operation(summary = "Actualizar registro", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @PutMapping("/{id}")
    public ResponseEntity<PedidoAutomaticoDTO> actualizar(@PathVariable Long id,
            @Valid @RequestBody PedidoAutomaticoDTO dto) {
        return ResponseEntity.ok(pedidoAutomaticoService.actualizar(id, dto));
    }

    @Operation(summary = "Eliminar registro", responses = {

            @ApiResponse(responseCode = "200", description = "Operacion realizada correctamente"),

            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")

    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pedidoAutomaticoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> manejarNoEncontrado(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}

