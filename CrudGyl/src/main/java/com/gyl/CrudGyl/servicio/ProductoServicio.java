package com.gyl.CrudGyl.servicio;

import java.util.List;

import com.gyl.CrudGyl.dto.ProductoRequestDto;
import com.gyl.CrudGyl.dto.ProductoResponseDto;

public interface ProductoServicio {
    ProductoResponseDto crear(ProductoRequestDto dto);

    List<ProductoResponseDto> listar();

    ProductoResponseDto buscarPorId(Long idBuscado);

    List<ProductoResponseDto> buscarPorNombre(String nombreBuscado);

    ProductoResponseDto actualizar(Long id, ProductoRequestDto dto);

    void eliminar(Long id);
}