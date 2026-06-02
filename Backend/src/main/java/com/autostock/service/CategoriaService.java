package com.autostock.service;

import com.autostock.dto.CategoriaDTO;
import java.util.List;

public interface CategoriaService {

    CategoriaDTO crear(CategoriaDTO categoriaDTO);

    List<CategoriaDTO> listar();

    CategoriaDTO obtenerPorId(Long id);

    CategoriaDTO actualizar(Long id, CategoriaDTO categoriaDTO);

    void eliminar(Long id);
}
