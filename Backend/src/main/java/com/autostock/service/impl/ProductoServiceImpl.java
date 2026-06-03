package com.autostock.service.impl;

import com.autostock.dto.ProductoDTO;
import com.autostock.model.Categoria;
import com.autostock.model.Producto;
import com.autostock.model.Proveedor;
import com.autostock.repository.CategoriaRepository;
import com.autostock.repository.ProductoRepository;
import com.autostock.repository.ProveedorRepository;
import com.autostock.service.ProductoService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProveedorRepository proveedorRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository,
            CategoriaRepository categoriaRepository,
            ProveedorRepository proveedorRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public Producto crearProducto(ProductoDTO dto) {
        Categoria categoria = buscarCategoria(dto.getCategoriaId());
        Proveedor proveedor = buscarProveedor(dto.getProveedorId());

        Producto producto = new Producto();
        asignarDatos(producto, dto, categoria, proveedor);
        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto obtenerPorId(Long id) {
        return buscarProducto(id);
    }

    @Override
    public Producto actualizarProducto(Long id, ProductoDTO dto) {
        Producto producto = buscarProducto(id);
        Categoria categoria = buscarCategoria(dto.getCategoriaId());
        Proveedor proveedor = buscarProveedor(dto.getProveedorId());

        asignarDatos(producto, dto, categoria, proveedor);
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        Producto producto = buscarProducto(id);
        productoRepository.delete(producto);
    }

    private Producto buscarProducto(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con id: " + id));
    }

    private Categoria buscarCategoria(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoria no encontrada con id: " + id));
    }

    private Proveedor buscarProveedor(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Proveedor no encontrado con id: " + id));
    }

    private void asignarDatos(Producto producto, ProductoDTO dto, Categoria categoria,
            Proveedor proveedor) {
        producto.setNombre(dto.getNombre());
        producto.setCodigo(dto.getCodigo());
        producto.setDescripcion(dto.getDescripcion());
        producto.setCantidad(dto.getCantidad());
        producto.setPrecioUnitario(dto.getPrecioUnitario());
        producto.setUbicacion(dto.getUbicacion());
        producto.setEstado(dto.getEstado());
        producto.setCategoria(categoria);
        producto.setProveedor(proveedor);
    }
}
