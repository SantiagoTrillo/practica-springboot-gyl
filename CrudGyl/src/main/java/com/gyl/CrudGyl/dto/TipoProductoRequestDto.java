package com.gyl.CrudGyl.dto;

import jakarta.validation.constraints.NotBlank;

public record TipoProductoRequestDto(
        @NotBlank(message = "El nombre no puede estar vacío.")
        String nombre,
        @NotBlank(message = "La descripción no puede estar vacía.")
        String descripcion
) {}