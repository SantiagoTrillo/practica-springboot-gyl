package com.gyl.CrudGyl.servicio;

import java.util.List;

import com.gyl.CrudGyl.dto.TipoProductoRequestDto;
import com.gyl.CrudGyl.dto.TipoProductoResponseDto;

public interface TipoProductoServicio {
    TipoProductoResponseDto crear(TipoProductoRequestDto dto);

    List<TipoProductoResponseDto> listar();

    TipoProductoResponseDto buscarPorId(Long idBuscado);

    List<TipoProductoResponseDto> buscarPorNombre(String nombreBuscado);

    TipoProductoResponseDto actualizar(Long idBuscado, TipoProductoRequestDto dto);

    void eliminar(Long idBuscado);
}