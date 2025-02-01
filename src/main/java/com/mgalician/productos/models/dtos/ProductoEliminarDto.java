package com.mgalician.productos.models.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductoEliminarDto {
@NotNull(message = "El id es obligatorio")
    private int id;
}
