package com.gyl.CrudGyl.mapper;

import com.gyl.CrudGyl.dto.request.ProductoRequestDto;
import com.gyl.CrudGyl.dto.response.ProductoResponseDto;
import com.gyl.CrudGyl.entidad.Producto;
import com.gyl.CrudGyl.entidad.TipoProducto;

public class ProductoMapper {
    private ProductoMapper() {}

    public static Producto toEntity(ProductoRequestDto dto, TipoProducto tipoProducto) {
        Producto producto = new Producto();

        producto.setNombre(dto.nombre());
        producto.setPrecio(dto.precio());
        producto.setStock(dto.stock());
        producto.setTipoProducto(tipoProducto);

        return producto;
    }

    public static ProductoResponseDto toResponseDto(Producto producto) {
        Long idTipoProducto = producto.getTipoProducto() != null ? producto.getTipoProducto().getId() : null;
        return new ProductoResponseDto(producto.getId(), producto.getNombre(), producto.getPrecio(),
                                       producto.getStock(), idTipoProducto);
    }

    public static void actualizarEntidad(Producto producto, ProductoRequestDto dto, TipoProducto tipoProducto) {
        producto.setNombre(dto.nombre());
        producto.setPrecio(dto.precio());
        producto.setStock(dto.stock());
        producto.setTipoProducto(tipoProducto);
    }
}