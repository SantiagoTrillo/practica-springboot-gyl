package com.gyl.CrudGyl.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DetalleVentaRequestDto(
        @NotNull(message = "La cantidad del producto es obligatoria")
        @Min(value = 1, message = "La cantidad del producto no puede ser negativa")
        Integer cantidadProducto,
        @NotNull(message = "El id del producto es obligatorio")
        @Min(value = 1, message = "El id del producto no puede ser negativo")
        Long idProducto
) {}