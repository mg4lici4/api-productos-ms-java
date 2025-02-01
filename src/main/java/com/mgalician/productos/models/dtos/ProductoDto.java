package com.mgalician.productos.models.dtos;

import lombok.Data;

@Data
public class ProductoDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer cantidad;
    private Boolean bloqueoLogico;
}
