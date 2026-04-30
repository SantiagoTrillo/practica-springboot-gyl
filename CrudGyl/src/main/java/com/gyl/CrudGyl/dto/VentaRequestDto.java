package com.gyl.CrudGyl.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record VentaRequestDto(
        @NotNull(message = "El id del cliente es obligatorio")
        @Min(value = 1, message = "El id del cliente no puede ser negativo")
        Long idCliente,
        @NotNull(message = "La lista de detalles es obligatoria")
        @NotEmpty(message = "La lista de detalles no puede estar vacía")
        List<DetalleVentaRequestDto> detallesVenta
) {}