package com.autostock.service.impl;

import com.autostock.dto.AlertaStockDTO;
import com.autostock.model.AlertaStock;
import com.autostock.repository.AlertaStockRepository;
import com.autostock.service.AlertaStockService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AlertaStockServiceImpl implements AlertaStockService {

    private final AlertaStockRepository alertaStockRepository;

    public AlertaStockServiceImpl(AlertaStockRepository alertaStockRepository) {
        this.alertaStockRepository = alertaStockRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlertaStockDTO> listarTodos() {
        return alertaStockRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AlertaStockDTO buscarPorId(Long id) {
        return toDTO(buscarAlertaStock(id));
    }

    @Override
    public AlertaStockDTO guardar(AlertaStockDTO dto) {
        AlertaStock alertaStock = new AlertaStock();
        asignarDatos(alertaStock, dto);
        alertaStock.generarAlerta();
        return toDTO(alertaStockRepository.save(alertaStock));
    }

    @Override
    public AlertaStockDTO actualizar(Long id, AlertaStockDTO dto) {
        AlertaStock alertaStock = buscarAlertaStock(id);
        asignarDatos(alertaStock, dto);
        alertaStock.generarAlerta();
        return toDTO(alertaStockRepository.save(alertaStock));
    }

    @Override
    public void eliminar(Long id) {
        AlertaStock alertaStock = buscarAlertaStock(id);
        alertaStockRepository.delete(alertaStock);
    }

    private AlertaStock buscarAlertaStock(Long id) {
        return alertaStockRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("AlertaStock no encontrada con id: " + id));
    }

    private void asignarDatos(AlertaStock alertaStock, AlertaStockDTO dto) {
        alertaStock.setNivelActual(dto.getNivelActual());
        alertaStock.setNivelMinimo(dto.getNivelMinimo());
        alertaStock.setFecha(dto.getFecha());
    }

    private AlertaStockDTO toDTO(AlertaStock alertaStock) {
        AlertaStockDTO dto = new AlertaStockDTO();
        dto.setId(alertaStock.getId());
        dto.setNivelActual(alertaStock.getNivelActual());
        dto.setNivelMinimo(alertaStock.getNivelMinimo());
        dto.setFecha(alertaStock.getFecha());
        return dto;
    }
}
