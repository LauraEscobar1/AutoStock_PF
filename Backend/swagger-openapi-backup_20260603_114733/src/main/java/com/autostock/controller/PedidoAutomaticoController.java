package com.autostock.controller;

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
@RequestMapping("/api/pedidos-automaticos")
public class PedidoAutomaticoController {

    private final PedidoAutomaticoService pedidoAutomaticoService;

    public PedidoAutomaticoController(PedidoAutomaticoService pedidoAutomaticoService) {
        this.pedidoAutomaticoService = pedidoAutomaticoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoAutomaticoDTO>> listarTodos() {
        return ResponseEntity.ok(pedidoAutomaticoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoAutomaticoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoAutomaticoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PedidoAutomaticoDTO> guardar(@RequestBody PedidoAutomaticoDTO dto) {
        PedidoAutomaticoDTO pedidoAutomaticoGuardado = pedidoAutomaticoService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoAutomaticoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoAutomaticoDTO> actualizar(@PathVariable Long id,
            @RequestBody PedidoAutomaticoDTO dto) {
        return ResponseEntity.ok(pedidoAutomaticoService.actualizar(id, dto));
    }

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
