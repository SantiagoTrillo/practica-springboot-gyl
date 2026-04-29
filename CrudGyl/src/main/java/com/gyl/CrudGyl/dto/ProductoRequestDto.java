package com.gyl.CrudGyl.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductoRequestDto(
        @NotBlank(message = "El nombre no puede estar vacío.")
        String nombre,
        @NotNull(message = "El precio es obligatorio")
        @Min(value = 1, message = "El precio no puede ser negativo")
        Double precio,
        @NotNull(message = "El stock es obligatorio")
        @Positive(message = "El stock debe ser positivo.")
        Integer stock,
        @NotNull
        @Min(value = 1, message = "El id del tipo de producto no puede ser negativo")
        Long idTipoProducto
) {}