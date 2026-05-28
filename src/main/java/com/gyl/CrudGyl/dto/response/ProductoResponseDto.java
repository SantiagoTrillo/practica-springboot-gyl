package com.gyl.CrudGyl.dto.response;

public record ProductoResponseDto(Long id, String nombre, Double precio, Integer stock, Long idTipoProducto) {}