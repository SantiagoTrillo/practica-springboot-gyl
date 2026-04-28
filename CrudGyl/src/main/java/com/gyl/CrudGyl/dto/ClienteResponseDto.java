package com.gyl.CrudGyl.dto;

public record ClienteResponseDto(
        Long id, String nombre, String apellido, String correo, String telefono, String direccion
) {}