package com.gyl.CrudGyl.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TipoProductoRequestDto(
        @NotBlank(message = "El nombre es obligatorio y no puede estar vacío.")
        String nombre,
        @NotBlank(message = "La descripción es obligatoria y no puede estar vacía.")
        String descripcion
) {}