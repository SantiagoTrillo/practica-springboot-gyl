package com.gyl.CrudGyl.dto;

import java.time.LocalDateTime;

public record VentaResponseDto(Long id, LocalDateTime fechaVenta, Double total, Long idCliente) {}