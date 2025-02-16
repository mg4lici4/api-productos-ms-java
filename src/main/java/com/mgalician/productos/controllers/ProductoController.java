package com.mgalician.productos.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgalician.productos.models.dtos.ProductoActualizarDto;
import com.mgalician.productos.models.dtos.ProductoAgregarDto;
import com.mgalician.productos.models.dtos.ProductoDto;
import com.mgalician.productos.models.dtos.ProductoEliminarDto;
import com.mgalician.productos.models.dtos.RespuestaGenericaDto;
import com.mgalician.productos.services.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDto>> obtenerTodos() {
        try {
            List<ProductoDto> productos = productoService.obtenerTodos();
            return new ResponseEntity<>(productos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<RespuestaGenericaDto> insertarProducto(
            @Validated @RequestBody ProductoAgregarDto productoAgregarDto) {
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        respuestaGenericaDto.setMensaje(
                productoService.insertarProducto(productoAgregarDto.getNombre(), productoAgregarDto.getDescripcion(),
                        productoAgregarDto.getPrecio().doubleValue(), productoAgregarDto.getCantidad()));
        return ResponseEntity.ok(respuestaGenericaDto);
    }

    @PutMapping()
    public ResponseEntity<RespuestaGenericaDto> actualizarProducto(
            @Validated @RequestBody ProductoActualizarDto productoActualizarDto) {
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        respuestaGenericaDto.setMensaje(productoService.actualizarProducto(productoActualizarDto.getId(),
                productoActualizarDto.getNombre(), productoActualizarDto.getDescripcion(),
                productoActualizarDto.getPrecio(), productoActualizarDto.getCantidad()));

        return ResponseEntity.ok(respuestaGenericaDto);
    }

    @DeleteMapping()
    public ResponseEntity<RespuestaGenericaDto> eliminarProducto(
            @Validated @RequestBody ProductoEliminarDto productoEliminarDto) {
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        respuestaGenericaDto.setMensaje(productoService.eliminarProductoPorId(productoEliminarDto.getId()));

        return ResponseEntity.ok(respuestaGenericaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProducto(@PathVariable Integer id) {
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        Map<String, Object> resultado = productoService.buscarProductoPorId(id);

        String mensaje = (String) resultado.get("Mensaje");
        if (mensaje != null && mensaje.contains("exitosamente")) {
            ProductoDto productoDto = new ProductoDto();
            productoDto.setId((long) id);
            productoDto.setNombre((String) resultado.get("Nombre"));
            productoDto.setDescripcion((String) resultado.get("Descripcion"));
            productoDto.setPrecio((double) resultado.get("Precio"));
            productoDto.setCantidad((Integer) resultado.get("Cantidad"));
            productoDto.setBloqueoLogico((boolean) resultado.get("BloqueoLogico"));

            respuestaGenericaDto.setDatos(productoDto);
            return ResponseEntity.ok(respuestaGenericaDto);
        } else {
            respuestaGenericaDto.setMensaje(mensaje);
            return ResponseEntity.badRequest().body(respuestaGenericaDto);
        }
    }
}
