package com.gyl.CrudGyl.controlador;

import java.util.List;

import com.gyl.CrudGyl.entidad.TipoProducto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.gyl.CrudGyl.dto.ProductoRequestDto;
import com.gyl.CrudGyl.dto.ProductoResponseDto;
import com.gyl.CrudGyl.servicio.ProductoServicio;

@RestController
@RequestMapping("/api/productos")
public class ProductoControlador {
    private final ProductoServicio productoServicio;

    public ProductoControlador(ProductoServicio productoServicio) {this.productoServicio = productoServicio;}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoResponseDto crear(@Valid @RequestBody ProductoRequestDto dto, TipoProducto tipoProducto) {
        return productoServicio.crear(dto, tipoProducto);
    }

    @GetMapping
    public List<ProductoResponseDto> listar() {return productoServicio.listar();}

    @GetMapping("/{idBuscado}")
    public ProductoResponseDto buscarPorId(@PathVariable Long idBuscado) {
        return productoServicio.buscarPorId(idBuscado);
    }

    @GetMapping("/nombre")
    public List<ProductoResponseDto> buscarPorNombre(@RequestParam String nombre) {
        return productoServicio.buscarPorNombre(nombre);
    }

    @PostMapping("/{idBuscado}")
    public ProductoResponseDto actualizar(@PathVariable Long idBuscado, @Valid @RequestBody ProductoRequestDto dto, TipoProducto tipoProducto) {
        return productoServicio.actualizar(idBuscado, dto, tipoProducto);
    }

    @DeleteMapping("/{idBuscado}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long idBuscado) {
        productoServicio.eliminar(idBuscado);
    }
}