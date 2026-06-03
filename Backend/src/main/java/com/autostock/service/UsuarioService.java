package com.autostock.service;

import com.autostock.dto.UsuarioDTO;
import java.util.List;

public interface UsuarioService {

    List<UsuarioDTO> listarTodos();

    UsuarioDTO buscarPorId(Long id);

    UsuarioDTO guardar(UsuarioDTO dto);

    UsuarioDTO actualizar(Long id, UsuarioDTO dto);

    void eliminar(Long id);
}
