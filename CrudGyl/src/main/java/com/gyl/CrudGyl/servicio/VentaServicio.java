package com.gyl.CrudGyl.servicio;

import com.gyl.CrudGyl.dto.VentaRequestDto;
import com.gyl.CrudGyl.dto.VentaResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface VentaServicio {
    VentaResponseDto crear(VentaRequestDto dto);

    List<VentaResponseDto> listar();

    VentaResponseDto buscarPorId(Long idBuscado);

    List<VentaResponseDto> buscarPorFechaVenta(LocalDateTime fechaVentaBuscada);

    List<VentaResponseDto> buscarPorRangoFechaVenta(LocalDateTime inicioRango, LocalDateTime finRango);
}