package com.mgalician.productos.models.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "PRODUCTOS")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "uspInsertarProducto", procedureName = "uspInsertarProducto", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "Nombre", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "Descripcion", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "Precio", type = Double.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "Cantidad", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "Mensaje", type = String.class)
        }),
        @NamedStoredProcedureQuery(name = "uspActualizarProducto", procedureName = "uspActualizarProducto", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "Id", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "Nombre", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "Descripcion", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "Precio", type = Double.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "Cantidad", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "Mensaje", type = String.class)
        }),
        @NamedStoredProcedureQuery(name = "uspEliminarProductoPorId", procedureName = "uspEliminarProductoPorId", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "Id", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "Mensaje", type = String.class)
        }),
        @NamedStoredProcedureQuery(name = "uspBuscarProductoPorId", procedureName = "uspBuscarProductoPorId", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "Id", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "Nombre", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "Descripcion", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "Precio", type = Double.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "Cantidad", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "BloqueoLogico", type = Boolean.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "FechaCreacion", type = LocalDateTime.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "FechaModificacion", type = LocalDateTime.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "Mensaje", type = String.class)
        })
})

public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "PRECIO")
    private Double precio;

    @Column(name = "CANTIDAD")
    private Integer cantidad;

    @Column(name = "BLOQUEO_LOGICO")
    private boolean bloqueoLogico;

    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;

    @Column(name = "FECHA_MODIFICACION")
    private LocalDateTime fechaModificacion;
}
