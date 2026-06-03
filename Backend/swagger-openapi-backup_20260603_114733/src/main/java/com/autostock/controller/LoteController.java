package com.autostock.controller;

import com.autostock.dto.LoteDTO;
import com.autostock.service.LoteService;
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
@RequestMapping("/api/lotes")
public class LoteController {

    private final LoteService loteService;

    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @GetMapping
    public ResponseEntity<List<LoteDTO>> listarTodos() {
        return ResponseEntity.ok(loteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(loteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<LoteDTO> guardar(@RequestBody LoteDTO dto) {
        LoteDTO loteGuardado = loteService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(loteGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoteDTO> actualizar(@PathVariable Long id, @RequestBody LoteDTO dto) {
        return ResponseEntity.ok(loteService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        loteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> manejarNoEncontrado(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
