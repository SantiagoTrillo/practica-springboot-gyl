package com.gyl.CrudGyl.servicio;

import com.gyl.CrudGyl.dto.request.ProductoRequestDto;
import com.gyl.CrudGyl.dto.response.ProductoResponseDto;

import java.util.List;

public interface ProductoServicio {
    ProductoResponseDto crear(ProductoRequestDto dto);

    List<ProductoResponseDto> listar();

    ProductoResponseDto buscarPorId(Long idBuscado);

    List<ProductoResponseDto> buscarPorNombre(String nombreBuscado);

    ProductoResponseDto actualizar(Long idBuscado, ProductoRequestDto dto);

    void eliminar(Long idBuscado);
}