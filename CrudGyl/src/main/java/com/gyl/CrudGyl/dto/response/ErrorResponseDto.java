package com.gyl.CrudGyl.dto.response;

import java.time.LocalDateTime;

public record ErrorResponseDto(String error, String mensaje, int estado, LocalDateTime fecha) {}