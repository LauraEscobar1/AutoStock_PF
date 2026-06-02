package com.autostock.service;

import com.autostock.dto.ProveedorDTO;
import java.util.List;

public interface ProveedorService {

    ProveedorDTO crear(ProveedorDTO proveedorDTO);

    List<ProveedorDTO> listar();

    ProveedorDTO obtenerPorId(Long id);

    ProveedorDTO actualizar(Long id, ProveedorDTO proveedorDTO);

    void eliminar(Long id);
}
