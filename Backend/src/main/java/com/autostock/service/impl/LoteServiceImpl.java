package com.autostock.service.impl;

import com.autostock.dto.LoteDTO;
import com.autostock.model.Lote;
import com.autostock.model.Producto;
import com.autostock.repository.LoteRepository;
import com.autostock.repository.ProductoRepository;
import com.autostock.service.LoteService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoteServiceImpl implements LoteService {

    private final LoteRepository loteRepository;
    private final ProductoRepository productoRepository;

    public LoteServiceImpl(LoteRepository loteRepository, ProductoRepository productoRepository) {
        this.loteRepository = loteRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoteDTO> listarTodos() {
        return loteRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LoteDTO buscarPorId(Long id) {
        return toDTO(buscarLote(id));
    }

    @Override
    public LoteDTO guardar(LoteDTO dto) {
        Producto producto = buscarProducto(dto.getProductoId());

        Lote lote = new Lote();
        lote.setNumero(dto.getNumero());
        lote.setFechaVencimiento(dto.getFechaVencimiento());
        lote.setProducto(producto);

        return toDTO(loteRepository.save(lote));
    }

    @Override
    public LoteDTO actualizar(Long id, LoteDTO dto) {
        Lote lote = buscarLote(id);
        Producto producto = buscarProducto(dto.getProductoId());

        lote.setNumero(dto.getNumero());
        lote.setFechaVencimiento(dto.getFechaVencimiento());
        lote.setProducto(producto);

        return toDTO(loteRepository.save(lote));
    }

    @Override
    public void eliminar(Long id) {
        Lote lote = buscarLote(id);
        loteRepository.delete(lote);
    }

    private Lote buscarLote(Long id) {
        return loteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lote no encontrado con id: " + id));
    }

    private Producto buscarProducto(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con id: " + id));
    }

    private LoteDTO toDTO(Lote lote) {
        LoteDTO dto = new LoteDTO();
        dto.setId(lote.getId());
        dto.setNumero(lote.getNumero());
        dto.setFechaVencimiento(lote.getFechaVencimiento());
        dto.setProductoId(lote.getProducto().getId());
        return dto;
    }
}
