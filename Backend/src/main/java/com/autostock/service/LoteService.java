package com.autostock.service;

import com.autostock.dto.LoteDTO;
import java.util.List;

public interface LoteService {

    List<LoteDTO> listarTodos();

    LoteDTO buscarPorId(Long id);

    LoteDTO guardar(LoteDTO dto);

    LoteDTO actualizar(Long id, LoteDTO dto);

    void eliminar(Long id);
}
