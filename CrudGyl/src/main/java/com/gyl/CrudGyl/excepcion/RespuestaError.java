package com.gyl.CrudGyl.excepcion;

import java.time.LocalDateTime;
import java.util.Map;

public record RespuestaError(LocalDateTime fecha, int estado, String error, String mensaje, String path,
        Map<String, String> erroresValidacion
) {}