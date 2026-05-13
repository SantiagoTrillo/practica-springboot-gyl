package com.gyl.CrudGyl.dto.request;

import jakarta.validation.constraints.NotBlank;

public record IniciarSesionRequestDto(
        @NotBlank(message = "El nombre de usuario es obligatorio y no puede estar vacío")
        String nombreUsuario,
        @NotBlank(message = "La contraseña es obligatoria y no puede estar vacía")
        String contraseña
) {}