package com.mgalician.productos.models.dtos;

import lombok.Data;

@Data
public class RespuestaGenericaDto {
    private String mensaje;
    private Object datos;
}
