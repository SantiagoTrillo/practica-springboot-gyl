package com.gyl.CrudGyl.servicio;

import java.util.List;

import com.gyl.CrudGyl.dto.ProductoRequestDto;
import com.gyl.CrudGyl.dto.ProductoResponseDto;

public interface ServicioProducto {
    ProductoResponseDto crear(ProductoRequestDto dto);

    List<ProductoResponseDto> listar();

    ProductoResponseDto buscarPorId(Long idBuscado);

    ProductoResponseDto actualizar(Long id, ProductoRequestDto dto);

    void eliminar(Long id);
}