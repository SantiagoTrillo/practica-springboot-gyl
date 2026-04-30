package com.gyl.CrudGyl.servicio.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gyl.CrudGyl.dto.ProductoRequestDto;
import com.gyl.CrudGyl.dto.ProductoResponseDto;
import com.gyl.CrudGyl.entidad.Producto;
import com.gyl.CrudGyl.entidad.TipoProducto;
import com.gyl.CrudGyl.excepcion.RecursoNoEncontradoExcepcion;
import com.gyl.CrudGyl.mapper.ProductoMapper;
import com.gyl.CrudGyl.repositorio.ProductoRepositorio;
import com.gyl.CrudGyl.repositorio.TipoProductoRepositorio;
import com.gyl.CrudGyl.servicio.ProductoServicio;

@Service
public class ProductoServicioImpl implements ProductoServicio {
    private final ProductoRepositorio productoRepositorio;
    private final TipoProductoRepositorio tipoProductoRepositorio;

    public ProductoServicioImpl(ProductoRepositorio productoRepositorio,
                                TipoProductoRepositorio tipoProductoRepositorio) {
        this.productoRepositorio = productoRepositorio;
        this.tipoProductoRepositorio = tipoProductoRepositorio;
    }

    @Override
    public ProductoResponseDto crear(ProductoRequestDto dto) {
        TipoProducto tipoProductoBuscado = tipoProductoRepositorio.findById(dto.idTipoProducto())
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion(
                        "No se encontró el id " + dto.idTipoProducto()
                ));
        Producto productoTraducido = ProductoMapper.toEntity(dto, tipoProductoBuscado);
        Producto productoGuardado = productoRepositorio.save(productoTraducido);

        return ProductoMapper.toResponseDto(productoGuardado);
    }

    @Override
    public List<ProductoResponseDto> listar() {
        return productoRepositorio.findAll().stream().map(ProductoMapper::toResponseDto).toList();
    }

    @Override
    public ProductoResponseDto buscarPorId(Long idBuscado) {
        return productoRepositorio.findById(idBuscado).map(ProductoMapper::toResponseDto)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No se encontró el id " + idBuscado));
    }

    @Override
    public List<ProductoResponseDto> buscarPorNombre(String nombreBuscado) {
        return productoRepositorio.findByNombre(nombreBuscado).stream().map(ProductoMapper::toResponseDto).toList();
    }

    @Override
    public ProductoResponseDto actualizar(Long idBuscado, ProductoRequestDto dto) {
        Producto productoBuscado = productoRepositorio.findById(idBuscado)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No se encontró el id " + idBuscado));
        TipoProducto tipoProductoBuscado = tipoProductoRepositorio.findById(dto.idTipoProducto())
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion(
                        "No se encontró el id " + dto.idTipoProducto()
                ));
        ProductoMapper.actualizarEntidad(productoBuscado, dto, tipoProductoBuscado);
        Producto productoGuardado = productoRepositorio.save(productoBuscado);

        return ProductoMapper.toResponseDto(productoGuardado);
    }

    @Override
    public void eliminar(Long idBuscado) {
        Producto productoBuscado = productoRepositorio.findById(idBuscado)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No se encontró el id " + idBuscado));
        productoRepositorio.delete(productoBuscado);
    }
}