package com.gyl.CrudGyl.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDto(
        @NotBlank(message = "El nombre no puede estar vacío.")
        String nombre,
        @NotBlank(message = "El apellido no puede estar vacío.")
        String apellido,
        @NotBlank(message = "El correo no puede estar vacío.")
        String correo,
        @NotBlank(message = "El teléfono no puede estar vacío.")
        String telefono,
        @NotBlank(message = "La dirección no puede estar vacía.")
        String direccion
) {}