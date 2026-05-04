package com.gyl.CrudGyl.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DetalleVentaRequestDto(
        @NotNull(message = "La cantidad del producto es obligatoria")
        @Positive(message = "La cantidad del producto debe ser positivo.")
        Integer cantidadProducto,
        @NotNull(message = "El id del producto es obligatorio")
        @Positive(message = "El id del producto debe ser positivo.")
        Long idProducto
) {}