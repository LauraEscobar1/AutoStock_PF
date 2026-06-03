package com.autostock.service;

import com.autostock.dto.MovimientoDTO;
import java.util.List;

public interface MovimientoService {

    List<MovimientoDTO> listarTodos();

    MovimientoDTO buscarPorId(Long id);

    MovimientoDTO guardar(MovimientoDTO dto);

    MovimientoDTO actualizar(Long id, MovimientoDTO dto);

    void eliminar(Long id);
}
