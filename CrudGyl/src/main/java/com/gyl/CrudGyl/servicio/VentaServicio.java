package com.gyl.CrudGyl.servicio;

import java.time.LocalDateTime;
import java.util.List;

import com.gyl.CrudGyl.dto.VentaRequestDto;
import com.gyl.CrudGyl.dto.VentaResponseDto;

public interface VentaServicio {
    VentaResponseDto crear(VentaRequestDto dto);

    List<VentaResponseDto> listar();

    VentaResponseDto buscarPorId(Long idBuscado);

    List<VentaResponseDto> buscarPorFechaVenta(LocalDateTime fechaVentaBuscada);
}