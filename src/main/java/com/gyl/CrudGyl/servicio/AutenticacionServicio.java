package com.gyl.CrudGyl.servicio;

import com.gyl.CrudGyl.dto.request.IniciarSesionRequestDto;
import com.gyl.CrudGyl.dto.request.RegistroRequestDto;
import com.gyl.CrudGyl.dto.response.IniciarSesionResponseDto;
import com.gyl.CrudGyl.dto.response.RegistroResponseDto;

public interface AutenticacionServicio {
    RegistroResponseDto registrar(RegistroRequestDto dto);

    IniciarSesionResponseDto iniciarSesion(IniciarSesionRequestDto dto);
}