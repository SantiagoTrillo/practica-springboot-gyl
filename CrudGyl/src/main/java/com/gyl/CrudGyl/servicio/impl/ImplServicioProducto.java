package com.gyl.CrudGyl.servicio.impl;

import java.util.List;

import com.gyl.CrudGyl.entidad.Producto;
import com.gyl.CrudGyl.excepcion.ExcepcionRecursoNoEncontrado;
import com.gyl.CrudGyl.mapper.ProductoMapper;
import com.gyl.CrudGyl.repositorio.RepositorioProducto;
import org.springframework.stereotype.Service;

import com.gyl.CrudGyl.dto.ProductoRequestDto;
import com.gyl.CrudGyl.dto.ProductoResponseDto;
import com.gyl.CrudGyl.servicio.ServicioProducto;

@Service
public class ImplServicioProducto implements ServicioProducto {
    private final RepositorioProducto repositorioProducto;

    public ImplServicioProducto(RepositorioProducto repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }

    @Override
    public ProductoResponseDto crear(ProductoRequestDto dto) {
        Producto productoTraducido = ProductoMapper.toEntity(dto);
        Producto productoGuardado = repositorioProducto.save(productoTraducido);
        return ProductoMapper.toResponseDto(productoGuardado);
    }

    @Override
    public List<ProductoResponseDto> listar() {
        return repositorioProducto.findAll().stream().map(ProductoMapper::toResponseDto).toList();
    }

    @Override
    public ProductoResponseDto buscarPorId(Long idBuscado) {
        return repositorioProducto.findById(idBuscado).map(ProductoMapper::toResponseDto).orElseThrow(() -> new ExcepcionRecursoNoEncontrado("No se encontró el id " + idBuscado));
    }

    @Override
    public ProductoResponseDto actualizar(Long id, ProductoRequestDto dto) {
        Producto producto = repositorioProducto.findById(id).orElseThrow(() -> new ExcepcionRecursoNoEncontrado("No se encontró el id " + id));
        ProductoMapper.actualizarEntidad(producto, dto);
        Producto productoGuardado = repositorioProducto.save(producto);

        return ProductoMapper.toResponseDto(productoGuardado);
    }

    @Override
    public void eliminar(Long id) {
        Producto producto = repositorioProducto.findById(id).orElseThrow(() -> new ExcepcionRecursoNoEncontrado("No se encontró el id " + id));
        repositorioProducto.delete(producto);
    }
}