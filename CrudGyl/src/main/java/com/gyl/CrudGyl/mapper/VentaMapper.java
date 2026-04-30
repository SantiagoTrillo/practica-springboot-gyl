package com.gyl.CrudGyl.mapper;

import com.gyl.CrudGyl.dto.VentaRequestDto;
import com.gyl.CrudGyl.dto.VentaResponseDto;
import com.gyl.CrudGyl.entidad.Cliente;
import com.gyl.CrudGyl.entidad.Venta;

public class VentaMapper {
    public VentaMapper() {}

    public static Venta toEntity(VentaRequestDto dto, Cliente cliente) {
        Venta venta = new Venta();

        venta.setCliente(cliente);

        return venta;
    }

    public static VentaResponseDto toResponseDto(Venta venta) {
        Long idCliente = venta.getCliente() != null ? venta.getCliente().getId() : null;

        return new VentaResponseDto(venta.getId(), venta.getFechaVenta(), venta.getTotal(), idCliente,
                                    venta.getDetallesVenta().stream().map(DetalleVentaMapper::toResponseDto).toList());
    }
}