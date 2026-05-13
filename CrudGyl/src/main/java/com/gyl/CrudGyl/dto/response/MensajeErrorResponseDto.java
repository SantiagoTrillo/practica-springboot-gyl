package com.gyl.CrudGyl.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MensajeErrorResponseDto {
    private final String error, mensaje;
    private final int estado;
    private final LocalDateTime fecha;
    private List<String> detalles;

    public MensajeErrorResponseDto(String error, String mensaje, int estado, LocalDateTime fecha) {
        this.error = error;
        this.mensaje = mensaje;
        this.estado = estado;
        this.fecha = fecha;
    }

    public MensajeErrorResponseDto(String error, String mensaje, int estado, LocalDateTime fecha, List<String> detalles) {
        this.error = error;
        this.mensaje = mensaje;
        this.estado = estado;
        this.fecha = fecha;
        this.detalles = detalles;
    }
}