package com.autostock.service;

import com.autostock.dto.SucursalDTO;
import com.autostock.model.Sucursal;
import java.util.List;

public interface SucursalService {

    Sucursal crearSucursal(SucursalDTO dto);

    List<Sucursal> obtenerTodas();

    Sucursal obtenerPorId(Long id);

    Sucursal actualizarSucursal(Long id, SucursalDTO dto);

    void eliminarSucursal(Long id);
}
