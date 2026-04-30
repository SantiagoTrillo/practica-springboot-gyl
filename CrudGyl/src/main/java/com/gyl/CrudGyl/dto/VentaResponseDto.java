package com.gyl.CrudGyl.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public record VentaResponseDto(Long id, @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime fechaVenta,
                               Double total, Long idCliente, List<DetalleVentaResponseDto> detallesVenta) {}