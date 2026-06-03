package com.autostock.service.impl;

import com.autostock.dto.MovimientoDTO;
import com.autostock.model.Movimiento;
import com.autostock.model.Producto;
import com.autostock.model.Usuario;
import com.autostock.repository.MovimientoRepository;
import com.autostock.repository.ProductoRepository;
import com.autostock.repository.UsuarioRepository;
import com.autostock.service.MovimientoService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository,
            ProductoRepository productoRepository,
            UsuarioRepository usuarioRepository) {
        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovimientoDTO> listarTodos() {
        return movimientoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MovimientoDTO buscarPorId(Long id) {
        return toDTO(buscarMovimiento(id));
    }

    @Override
    public MovimientoDTO guardar(MovimientoDTO dto) {
        Producto producto = buscarProducto(dto.getProductoId());
        Usuario usuario = buscarUsuario(dto.getUsuarioId());

        Movimiento movimiento = new Movimiento();
        asignarDatos(movimiento, dto, producto, usuario);
        return toDTO(movimientoRepository.save(movimiento));
    }

    @Override
    public MovimientoDTO actualizar(Long id, MovimientoDTO dto) {
        Movimiento movimiento = buscarMovimiento(id);
        Producto producto = buscarProducto(dto.getProductoId());
        Usuario usuario = buscarUsuario(dto.getUsuarioId());

        asignarDatos(movimiento, dto, producto, usuario);
        return toDTO(movimientoRepository.save(movimiento));
    }

    @Override
    public void eliminar(Long id) {
        Movimiento movimiento = buscarMovimiento(id);
        movimientoRepository.delete(movimiento);
    }

    private Movimiento buscarMovimiento(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Movimiento no encontrado con id: " + id));
    }

    private Producto buscarProducto(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con id: " + id));
    }

    private Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + id));
    }

    private void asignarDatos(Movimiento movimiento, MovimientoDTO dto, Producto producto,
            Usuario usuario) {
        movimiento.setTipo(dto.getTipo());
        movimiento.setFechaHora(dto.getFechaHora());
        movimiento.setCantidad(dto.getCantidad());
        movimiento.setProducto(producto);
        movimiento.setUsuario(usuario);
    }

    private MovimientoDTO toDTO(Movimiento movimiento) {
        MovimientoDTO dto = new MovimientoDTO();
        dto.setId(movimiento.getId());
        dto.setTipo(movimiento.getTipo());
        dto.setFechaHora(movimiento.getFechaHora());
        dto.setCantidad(movimiento.getCantidad());
        dto.setProductoId(movimiento.getProducto().getId());
        dto.setUsuarioId(movimiento.getUsuario().getId());
        return dto;
    }
}
