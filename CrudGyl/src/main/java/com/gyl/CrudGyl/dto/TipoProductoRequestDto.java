package com.gyl.CrudGyl.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TipoProductoRequestDto(
        @NotNull(message = "El nombre es obligatorio")
        @NotBlank(message = "El nombre no puede estar vacío.")
        String nombre,
        @NotNull(message = "La descripción es obligatoria")
        @NotBlank(message = "La descripción no puede estar vacía.")
        String descripcion
) {}