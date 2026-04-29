package com.gyl.CrudGyl.servicio;

import java.util.List;

import com.gyl.CrudGyl.dto.ProductoRequestDto;
import com.gyl.CrudGyl.dto.ProductoResponseDto;
import com.gyl.CrudGyl.entidad.TipoProducto;

public interface ProductoServicio {
    ProductoResponseDto crear(ProductoRequestDto dto, TipoProducto tipoProducto);

    List<ProductoResponseDto> listar();

    ProductoResponseDto buscarPorId(Long idBuscado);

    List<ProductoResponseDto> buscarPorNombre(String nombreBuscado);

    ProductoResponseDto actualizar(Long idBuscado, ProductoRequestDto dto, TipoProducto tipoProducto);

    void eliminar(Long idBuscado);
}