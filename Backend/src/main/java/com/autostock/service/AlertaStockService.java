package com.autostock.service;

import com.autostock.dto.AlertaStockDTO;
import java.util.List;

public interface AlertaStockService {

    List<AlertaStockDTO> listarTodos();

    AlertaStockDTO buscarPorId(Long id);

    AlertaStockDTO guardar(AlertaStockDTO dto);

    AlertaStockDTO actualizar(Long id, AlertaStockDTO dto);

    void eliminar(Long id);
}
