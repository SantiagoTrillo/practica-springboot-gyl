package com.gyl.CrudGyl.dto;

import jakarta.validation.constraints.*;

public record ProductoRequestDto(
        @NotBlank(message = "El nombre es obligatorio y no puede estar vacío.")
        String nombre,
        @NotNull(message = "El precio es obligatorio")
        @DecimalMin(value = "0.1", message = "El precio no puede ser negativo")
        Double precio,
        @NotNull(message = "El stock es obligatorio")
        @Min(value = 0, message = "El stock no puede ser negativo")
        Integer stock,
        @NotNull(message = "El id del tipo de producto es obligatorio")
        @Positive(message = "El id del tipo de producto debe ser positivo.")
        Long idTipoProducto
) {}