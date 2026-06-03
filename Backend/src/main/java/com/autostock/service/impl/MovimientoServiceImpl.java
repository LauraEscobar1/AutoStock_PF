package com.autostock.service.impl;

import com.autostock.dto.MovimientoDTO;
import com.autostock.model.AlertaStock;
import com.autostock.model.Movimiento;
import com.autostock.model.PedidoAutomatico;
import com.autostock.model.Producto;
import com.autostock.model.TipoMovimiento;
import com.autostock.model.Usuario;
import com.autostock.repository.AlertaStockRepository;
import com.autostock.repository.MovimientoRepository;
import com.autostock.repository.PedidoAutomaticoRepository;
import com.autostock.repository.ProductoRepository;
import com.autostock.repository.UsuarioRepository;
import com.autostock.service.MovimientoService;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private final AlertaStockRepository alertaStockRepository;
    private final PedidoAutomaticoRepository pedidoAutomaticoRepository;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository,
            ProductoRepository productoRepository,
            UsuarioRepository usuarioRepository,
            AlertaStockRepository alertaStockRepository,
            PedidoAutomaticoRepository pedidoAutomaticoRepository) {
        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
        this.alertaStockRepository = alertaStockRepository;
        this.pedidoAutomaticoRepository = pedidoAutomaticoRepository;
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
    public MovimientoDTO registrarEntrada(MovimientoDTO dto) {
        return registrarMovimientoConStock(dto, TipoMovimiento.ENTRADA);
    }

    @Override
    public MovimientoDTO registrarSalida(MovimientoDTO dto) {
        return registrarMovimientoConStock(dto, TipoMovimiento.SALIDA);
    }

    @Override
    public MovimientoDTO registrarDevolucion(MovimientoDTO dto) {
        return registrarMovimientoConStock(dto, TipoMovimiento.DEVOLUCION_CLIENTE);
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

    private MovimientoDTO registrarMovimientoConStock(MovimientoDTO dto, TipoMovimiento tipo) {
        Producto producto = buscarProducto(dto.getProductoId());
        Usuario usuario = buscarUsuario(dto.getUsuarioId());
        Integer cantidadMovimiento = dto.getCantidad() != null ? dto.getCantidad() : 0;
        Integer cantidadActual = producto.getCantidad() != null ? producto.getCantidad() : 0;

        if (tipo == TipoMovimiento.SALIDA) {
            producto.setCantidad(cantidadActual - cantidadMovimiento);
        } else {
            producto.setCantidad(cantidadActual + cantidadMovimiento);
        }

        productoRepository.save(producto);
        crearAlertaYPedidoSiAplica(producto);

        Movimiento movimiento = new Movimiento();
        movimiento.setTipo(tipo);
        movimiento.setFechaHora(dto.getFechaHora() != null ? dto.getFechaHora() : LocalDateTime.now());
        movimiento.setCantidad(dto.getCantidad());
        movimiento.setProducto(producto);
        movimiento.setUsuario(usuario);

        return toDTO(movimientoRepository.save(movimiento));
    }

    private void crearAlertaYPedidoSiAplica(Producto producto) {
        if (producto.getCantidad() != null && producto.getCantidad() <= 5) {
            AlertaStock alertaStock = new AlertaStock();
            alertaStock.setNivelActual(producto.getCantidad());
            alertaStock.setNivelMinimo(5);
            alertaStock.setFecha(LocalDate.now());
            alertaStock.generarAlerta();
            alertaStockRepository.save(alertaStock);

            PedidoAutomatico pedidoAutomatico = new PedidoAutomatico();
            pedidoAutomatico.setCantidad(20);
            pedidoAutomatico.setFechaGenerado(LocalDate.now());
            pedidoAutomatico.generarPedido();
            pedidoAutomaticoRepository.save(pedidoAutomatico);
        }
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
