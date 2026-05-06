package com.gyl.CrudGyl.mapper;

import com.gyl.CrudGyl.dto.request.DetalleVentaRequestDto;
import com.gyl.CrudGyl.dto.response.DetalleVentaResponseDto;
import com.gyl.CrudGyl.entidad.DetalleVenta;
import com.gyl.CrudGyl.entidad.Producto;
import com.gyl.CrudGyl.entidad.Venta;

public class DetalleVentaMapper {
    public DetalleVentaMapper() {}

    public static DetalleVenta toEntity(DetalleVentaRequestDto dto, Venta venta, Producto producto, Double subtotal) {
        DetalleVenta detalleVentaNuevo = new DetalleVenta();

        detalleVentaNuevo.setVenta(venta);
        detalleVentaNuevo.setProducto(producto);
        detalleVentaNuevo.setCantidadProducto(dto.cantidadProducto());
        detalleVentaNuevo.setPrecioUnitario(producto.getPrecio());
        detalleVentaNuevo.setSubtotal(subtotal);

        return detalleVentaNuevo;
    }

    public static DetalleVentaResponseDto toResponseDto(DetalleVenta detalleVenta) {
        return new DetalleVentaResponseDto(
                detalleVenta.getId(), detalleVenta.getCantidadProducto(), detalleVenta.getPrecioUnitario(),
                detalleVenta.getSubtotal(), detalleVenta.getProducto().getId()
        );
    }
}