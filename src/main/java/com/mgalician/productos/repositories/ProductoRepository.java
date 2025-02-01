package com.mgalician.productos.repositories;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mgalician.productos.models.entities.ProductoEntity;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

    @Procedure(name = "uspInsertarProducto")
    String insertarProducto(
            @Param("Nombre") String nombre,
            @Param("Descripcion") String descripcion,
            @Param("Precio") double precio,
            @Param("Cantidad") int cantidad);

    @Procedure(name = "uspActualizarProducto")
    String actualizarProducto(
            @Param("Id") int id,
            @Param("Nombre") String nombre,
            @Param("Descripcion") String descripcion,
            @Param("Precio") double precio,
            @Param("Cantidad") int cantidad);

    @Procedure(name = "uspEliminarProductoPorId")
    String eliminarProductoPorId(
            @Param("Id") int id);

    @Procedure(name = "uspBuscarProductoPorId")
    Map<String, Object> buscarProductoPorId(
            @Param("Id") int id);
}
