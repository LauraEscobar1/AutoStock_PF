package com.autostock.service.impl;

import com.autostock.dto.RolDTO;
import com.autostock.model.Rol;
import com.autostock.repository.RolRepository;
import com.autostock.service.RolService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public Rol crearRol(RolDTO rolDTO) {
        Rol rol = new Rol();
        rol.setNombre(rolDTO.getNombre());
        rol.setDescripcion(rolDTO.getDescripcion());
        return rolRepository.save(rol);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Rol obtenerPorId(Long id) {
        return buscarRol(id);
    }

    @Override
    public Rol actualizarRol(Long id, RolDTO rolDTO) {
        Rol rol = buscarRol(id);
        rol.setNombre(rolDTO.getNombre());
        rol.setDescripcion(rolDTO.getDescripcion());
        return rolRepository.save(rol);
    }

    @Override
    public void eliminarRol(Long id) {
        Rol rol = buscarRol(id);
        rolRepository.delete(rol);
    }

    private Rol buscarRol(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rol no encontrado con id: " + id));
    }
}
