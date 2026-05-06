package com.gyl.CrudGyl.dto.response;

public record DetalleVentaResponseDto(
        Long id, Integer cantidadProducto, Double precioUnitario, Double subtotal, Long idProducto
) {}