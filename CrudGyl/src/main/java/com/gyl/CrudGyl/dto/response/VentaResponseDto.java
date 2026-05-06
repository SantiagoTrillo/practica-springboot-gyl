package com.gyl.CrudGyl.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record VentaResponseDto(
        Long id, @JsonFormat(pattern = "yyyy-MM-dd hh:mm a") LocalDateTime fechaVenta, Double total, Long idCliente,
        List<DetalleVentaResponseDto> detallesVenta
) {}