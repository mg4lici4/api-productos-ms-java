package com.mgalician.productos.services;

import java.util.List;
import java.util.Map;

import com.mgalician.productos.models.dtos.ProductoDto;

public interface ProductoService {
    List<ProductoDto> obtenerTodos();

    String insertarProducto(String nombre, String descripcion, double precio, int cantidad);

    String actualizarProducto(int id, String nombre, String descripcion, double precio, int cantidad);

    String eliminarProductoPorId(int id);

    Map<String, Object> buscarProductoPorId(int id);
}
