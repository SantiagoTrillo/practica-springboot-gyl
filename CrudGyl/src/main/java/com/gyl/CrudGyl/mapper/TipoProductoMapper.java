package com.gyl.CrudGyl.mapper;

import com.gyl.CrudGyl.dto.request.TipoProductoRequestDto;
import com.gyl.CrudGyl.dto.response.TipoProductoResponseDto;
import com.gyl.CrudGyl.entidad.TipoProducto;

public class TipoProductoMapper {
    public TipoProductoMapper() {}

    public static TipoProducto toEntity(TipoProductoRequestDto dto) {
        TipoProducto tipoProducto = new TipoProducto();

        tipoProducto.setNombre(dto.nombre());
        tipoProducto.setDescripcion(dto.descripcion());

        return tipoProducto;
    }

    public static TipoProductoResponseDto toResponseDto(TipoProducto tipoProducto) {
        return new TipoProductoResponseDto(tipoProducto.getId(), tipoProducto.getNombre(),
                tipoProducto.getDescripcion());
    }

    public static void actualizarEntidad(TipoProducto tipoProducto, TipoProductoRequestDto dto) {
        tipoProducto.setNombre(dto.nombre());
        tipoProducto.setDescripcion(dto.descripcion());
    }
}