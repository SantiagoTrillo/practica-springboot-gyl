package com.gyl.CrudGyl.servicio.impl;

import com.gyl.CrudGyl.dto.request.TipoProductoRequestDto;
import com.gyl.CrudGyl.dto.response.TipoProductoResponseDto;
import com.gyl.CrudGyl.entidad.TipoProducto;
import com.gyl.CrudGyl.excepcion.RecursoNoEncontradoExcepcion;
import com.gyl.CrudGyl.mapper.TipoProductoMapper;
import com.gyl.CrudGyl.repositorio.TipoProductoRepositorio;
import com.gyl.CrudGyl.servicio.TipoProductoServicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProductoServicioImpl implements TipoProductoServicio {
    private final TipoProductoRepositorio tipoProductoRepositorio;

    public TipoProductoServicioImpl(TipoProductoRepositorio tipoProductoRepositorio) {
        this.tipoProductoRepositorio = tipoProductoRepositorio;
    }

    @Override
    public TipoProductoResponseDto crear(TipoProductoRequestDto dto) {
        TipoProducto tipoProductoTraducido = TipoProductoMapper.toEntity(dto);
        TipoProducto tipoProductoGuardado = tipoProductoRepositorio.save(tipoProductoTraducido);

        return TipoProductoMapper.toResponseDto(tipoProductoGuardado);
    }

    @Override
    public List<TipoProductoResponseDto> listar() {
        return tipoProductoRepositorio.findAll().stream().map(TipoProductoMapper::toResponseDto).toList();
    }

    @Override
    public TipoProductoResponseDto buscarPorId(Long idBuscado) {
        return tipoProductoRepositorio.findById(idBuscado).map(TipoProductoMapper::toResponseDto)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion(
                        "No se encontró el tipo de producto con id" + idBuscado
                ));
    }

    @Override
    public List<TipoProductoResponseDto> buscarPorNombre(String nombreBuscado) {
        return tipoProductoRepositorio.findByNombre(nombreBuscado).stream().map(TipoProductoMapper::toResponseDto)
                .toList();
    }

    @Override
    public TipoProductoResponseDto actualizar(Long idBuscado, TipoProductoRequestDto dto) {
        TipoProducto tipoProductoBuscado = tipoProductoRepositorio.findById(idBuscado)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion(
                        "No se encontró el tipo de producto con id " + idBuscado
                ));

        TipoProductoMapper.actualizarEntidad(tipoProductoBuscado, dto);

        TipoProducto tipoProductoActualizado = tipoProductoRepositorio.save(tipoProductoBuscado);

        return TipoProductoMapper.toResponseDto(tipoProductoActualizado);
    }

    @Override
    public void eliminar(Long idBuscado) {
        TipoProducto tipoProductoBuscado = tipoProductoRepositorio.findById(idBuscado)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion(
                        "No se encontró el tipo de producto con id " + idBuscado
                ));

        tipoProductoRepositorio.delete(tipoProductoBuscado);
    }
}