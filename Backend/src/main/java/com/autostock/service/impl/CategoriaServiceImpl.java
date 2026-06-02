package com.autostock.service.impl;

import com.autostock.dto.CategoriaDTO;
import com.autostock.model.Categoria;
import com.autostock.repository.CategoriaRepository;
import com.autostock.service.CategoriaService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public CategoriaDTO crear(CategoriaDTO categoriaDTO) {
        Categoria categoria = toEntity(categoriaDTO);
        Categoria categoriaGuardada = categoriaRepository.save(categoria);
        return toDTO(categoriaGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaDTO> listar() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaDTO obtenerPorId(Long id) {
        Categoria categoria = buscarCategoria(id);
        return toDTO(categoria);
    }

    @Override
    public CategoriaDTO actualizar(Long id, CategoriaDTO categoriaDTO) {
        Categoria categoria = buscarCategoria(id);
        categoria.setNombre(categoriaDTO.getNombre());

        Categoria categoriaActualizada = categoriaRepository.save(categoria);
        return toDTO(categoriaActualizada);
    }

    @Override
    public void eliminar(Long id) {
        Categoria categoria = buscarCategoria(id);
        categoriaRepository.delete(categoria);
    }

    private Categoria buscarCategoria(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoria no encontrada con id: " + id));
    }

    private CategoriaDTO toDTO(Categoria categoria) {
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .build();
    }

    private Categoria toEntity(CategoriaDTO categoriaDTO) {
        return Categoria.builder()
                .id(categoriaDTO.getId())
                .nombre(categoriaDTO.getNombre())
                .build();
    }
}
