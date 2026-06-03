package com.autostock.service.impl;

import com.autostock.dto.SucursalDTO;
import com.autostock.model.Sucursal;
import com.autostock.repository.SucursalRepository;
import com.autostock.service.SucursalService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;

    public SucursalServiceImpl(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public Sucursal crearSucursal(SucursalDTO dto) {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(dto.getNombre());
        sucursal.setUbicacion(dto.getUbicacion());
        return sucursalRepository.save(sucursal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sucursal> obtenerTodas() {
        return sucursalRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Sucursal obtenerPorId(Long id) {
        return buscarSucursal(id);
    }

    @Override
    public Sucursal actualizarSucursal(Long id, SucursalDTO dto) {
        Sucursal sucursal = buscarSucursal(id);
        sucursal.setNombre(dto.getNombre());
        sucursal.setUbicacion(dto.getUbicacion());
        return sucursalRepository.save(sucursal);
    }

    @Override
    public void eliminarSucursal(Long id) {
        Sucursal sucursal = buscarSucursal(id);
        sucursalRepository.delete(sucursal);
    }

    private Sucursal buscarSucursal(Long id) {
        return sucursalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Sucursal no encontrada con id: " + id));
    }
}
