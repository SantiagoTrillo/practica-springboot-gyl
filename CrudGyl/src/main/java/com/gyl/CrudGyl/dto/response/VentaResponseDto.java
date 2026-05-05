package com.gyl.CrudGyl.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public record VentaResponseDto(Long id, @JsonFormat(pattern = "yyyy-MM-dd hh:mm a") LocalDateTime fechaVenta,
                               Double total, Long idCliente, List<DetalleVentaResponseDto> detallesVenta) {}