package com.gyl.CrudGyl.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record VentaResponseDto(Long id, @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime fechaVenta, Double total, Long idCliente) {}