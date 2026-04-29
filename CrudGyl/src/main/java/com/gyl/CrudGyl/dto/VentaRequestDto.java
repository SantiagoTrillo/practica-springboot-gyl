package com.gyl.CrudGyl.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record VentaRequestDto(
        @NotNull(message = "El precio es obligatorio")
        @Min(value = 1, message = "El precio no puede ser negativo")
        Double total,
        @NotNull
        @Min(value = 1, message = "El id del cliente no puede ser negativo")
        Long idCliente
) {}