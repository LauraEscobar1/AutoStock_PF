package com.autostock.controller;

import com.autostock.service.ERPService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/erp")
@Tag(name = "ERP", description = "Operaciones de sincronizacion ERP")
public class ERPController {

    private final ERPService erpService;

    public ERPController(ERPService erpService) {
        this.erpService = erpService;
    }

    @PostMapping("/compras/sincronizar")
    @Operation(summary = "Sincronizar compras", responses = {
            @ApiResponse(responseCode = "200", description = "Compras sincronizadas correctamente")
    })
    public ResponseEntity<String> sincronizarCompras() {
        return ResponseEntity.ok(erpService.sincronizarCompras());
    }

    @PostMapping("/ventas/sincronizar")
    @Operation(summary = "Sincronizar ventas", responses = {
            @ApiResponse(responseCode = "200", description = "Ventas sincronizadas correctamente")
    })
    public ResponseEntity<String> sincronizarVentas() {
        return ResponseEntity.ok(erpService.sincronizarVentas());
    }

    @PostMapping("/facturas/sincronizar")
    @Operation(summary = "Sincronizar facturas", responses = {
            @ApiResponse(responseCode = "200", description = "Facturas sincronizadas correctamente")
    })
    public ResponseEntity<String> sincronizarFacturas() {
        return ResponseEntity.ok(erpService.sincronizarFacturas());
    }
}

