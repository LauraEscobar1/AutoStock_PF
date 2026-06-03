package com.autostock.service;

import com.autostock.dto.ProductoDTO;
import com.autostock.model.Producto;
import java.util.List;

public interface ProductoService {

    Producto crearProducto(ProductoDTO dto);

    List<Producto> obtenerTodos();

    Producto obtenerPorId(Long id);

    Producto actualizarProducto(Long id, ProductoDTO dto);

    void eliminarProducto(Long id);
}
