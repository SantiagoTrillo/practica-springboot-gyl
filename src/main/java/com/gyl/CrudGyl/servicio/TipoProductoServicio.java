package com.gyl.CrudGyl.servicio;

import com.gyl.CrudGyl.dto.request.TipoProductoRequestDto;
import com.gyl.CrudGyl.dto.response.TipoProductoResponseDto;

import java.util.List;

public interface TipoProductoServicio {
    TipoProductoResponseDto crear(TipoProductoRequestDto dto);

    List<TipoProductoResponseDto> listar();

    TipoProductoResponseDto buscarPorId(Long idBuscado);

    List<TipoProductoResponseDto> buscarPorNombre(String nombreBuscado);

    TipoProductoResponseDto actualizar(Long idBuscado, TipoProductoRequestDto dto);

    void eliminar(Long idBuscado);
}