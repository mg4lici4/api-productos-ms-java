package com.mgalician.productos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgalician.productos.models.dtos.ProductoDto;
import com.mgalician.productos.models.entities.ProductoEntity;
import com.mgalician.productos.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDto> obtenerTodos() {
        return productoRepository.findAll().stream().map(this::transformarEntityADto).collect(Collectors.toList());
    }

    @Override
    public String insertarProducto(String nombre, String descripcion, double precio, int cantidad) {
        return productoRepository.insertarProducto(nombre, descripcion, precio, cantidad);
    }

    @Override
    public String actualizarProducto(int id, String nombre, String descripcion, double precio, int cantidad) {
        return productoRepository.actualizarProducto(id, nombre, descripcion, precio, cantidad);
    }

    @Override
    public String eliminarProductoPorId(int id) {
        return productoRepository.eliminarProductoPorId(id);
    }

    @Override
    public Map<String, Object> buscarProductoPorId(int id) {
        return productoRepository.buscarProductoPorId(id);
    }

    private ProductoDto transformarEntityADto(ProductoEntity productoEntity) {
        ProductoDto productoDTO = new ProductoDto();
        productoDTO.setBloqueoLogico(productoEntity.isBloqueoLogico());
        productoDTO.setCantidad(productoEntity.getCantidad());
        productoDTO.setDescripcion(productoEntity.getDescripcion());
        productoDTO.setId(productoEntity.getId());
        productoDTO.setNombre(productoEntity.getNombre());
        productoDTO.setPrecio(productoEntity.getPrecio());
        return productoDTO;
    }

}
