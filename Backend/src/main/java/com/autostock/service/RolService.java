package com.autostock.service;

import com.autostock.dto.RolDTO;
import com.autostock.model.Rol;
import java.util.List;

public interface RolService {

    Rol crearRol(RolDTO rolDTO);

    List<Rol> obtenerTodos();

    Rol obtenerPorId(Long id);

    Rol actualizarRol(Long id, RolDTO rolDTO);

    void eliminarRol(Long id);
}
