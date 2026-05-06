package com.gyl.CrudGyl.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class MensajeErrorResponseDto {
    String error, mensaje;
    int estado;
    LocalDateTime fecha;
    List<String> detalles;

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