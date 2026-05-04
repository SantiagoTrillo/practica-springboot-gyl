package com.gyl.CrudGyl.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDto(
        @NotBlank(message = "El nombre es obligatorio y no puede estar vacío.")
        String nombre,
        @NotBlank(message = "El apellido es obligatorio y no puede estar vacío.")
        String apellido,
        @NotBlank(message = "El correo es obligatorio y no puede estar vacío.")
        @Email(message = "El correo no es válido")
        String correo,
        @NotBlank(message = "El teléfono es obligatorio y no puede estar vacío.")
        String telefono,
        @NotBlank(message = "La dirección es obligatoria y no puede estar vacía.")
        String direccion
) {}