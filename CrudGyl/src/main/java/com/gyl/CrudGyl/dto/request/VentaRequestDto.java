package com.gyl.CrudGyl.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record VentaRequestDto(
        @NotNull(message = "El id del cliente es obligatorio")
        @Positive(message = "El id del cliente debe ser positivo.")
        Long idCliente,
        @Valid
        @NotEmpty(message = "La lista de detalles es obligatoria y no puede estar vacía")
        List<DetalleVentaRequestDto> detallesVenta
) {}