package com.gyl.CrudGyl.mapper;

import com.gyl.CrudGyl.dto.DetalleVentaResponseDto;
import com.gyl.CrudGyl.entidad.DetalleVenta;

public class DetalleVentaMapper {
    public DetalleVentaMapper() {}

    public static DetalleVentaResponseDto toResponseDto(DetalleVenta detalleVenta) {
        return new DetalleVentaResponseDto(detalleVenta.getId(), detalleVenta.getCantidadProducto(),
                                            detalleVenta.getPrecioUnitario(), detalleVenta.getSubtotal(),
                                            detalleVenta.getProducto().getId());
    }
}