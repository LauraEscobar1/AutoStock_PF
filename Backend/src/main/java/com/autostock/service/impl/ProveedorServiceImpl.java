package com.autostock.service.impl;

import com.autostock.dto.ProveedorDTO;
import com.autostock.model.Proveedor;
import com.autostock.repository.ProveedorRepository;
import com.autostock.service.ProveedorService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    @Override
    public ProveedorDTO crear(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = toEntity(proveedorDTO);
        Proveedor proveedorGuardado = proveedorRepository.save(proveedor);
        return toDTO(proveedorGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProveedorDTO> listar() {
        return proveedorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProveedorDTO obtenerPorId(Long id) {
        Proveedor proveedor = buscarProveedor(id);
        return toDTO(proveedor);
    }

    @Override
    public ProveedorDTO actualizar(Long id, ProveedorDTO proveedorDTO) {
        Proveedor proveedor = buscarProveedor(id);
        proveedor.setNombre(proveedorDTO.getNombre());
        proveedor.setTelefono(proveedorDTO.getTelefono());
        proveedor.setCorreo(proveedorDTO.getCorreo());

        Proveedor proveedorActualizado = proveedorRepository.save(proveedor);
        return toDTO(proveedorActualizado);
    }

    @Override
    public void eliminar(Long id) {
        Proveedor proveedor = buscarProveedor(id);
        proveedorRepository.delete(proveedor);
    }

    private Proveedor buscarProveedor(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Proveedor no encontrado con id: " + id));
    }

    private ProveedorDTO toDTO(Proveedor proveedor) {
        return ProveedorDTO.builder()
                .id(proveedor.getId())
                .nombre(proveedor.getNombre())
                .telefono(proveedor.getTelefono())
                .correo(proveedor.getCorreo())
                .build();
    }

    private Proveedor toEntity(ProveedorDTO proveedorDTO) {
        return Proveedor.builder()
                .id(proveedorDTO.getId())
                .nombre(proveedorDTO.getNombre())
                .telefono(proveedorDTO.getTelefono())
                .correo(proveedorDTO.getCorreo())
                .build();
    }
}
