package com.gyl.CrudGyl.mapper;

import com.gyl.CrudGyl.dto.response.VentaResponseDto;
import com.gyl.CrudGyl.entidad.Cliente;
import com.gyl.CrudGyl.entidad.Venta;

public class VentaMapper {
    public VentaMapper() {}

    public static Venta toEntity(Cliente cliente) {
        Venta venta = new Venta();

        venta.setCliente(cliente);

        return venta;
    }

    public static VentaResponseDto toResponseDto(Venta venta) {
        return new VentaResponseDto(
                venta.getId(), venta.getFechaVenta(), venta.getTotal(), venta.getCliente().getId(),
                venta.getDetallesVenta().stream().map(DetalleVentaMapper::toResponseDto).toList()
        );
    }
}