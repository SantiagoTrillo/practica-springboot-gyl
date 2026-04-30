package com.gyl.CrudGyl.dto;

public record DetalleVentaResponseDto(Long id, Integer cantidadProducto, Double precioUnitario, Double subtotal,
                                      Long idProducto) {}