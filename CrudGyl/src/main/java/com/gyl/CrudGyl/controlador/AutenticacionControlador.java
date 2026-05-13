package com.gyl.CrudGyl.controlador;

import com.gyl.CrudGyl.dto.request.IniciarSesionRequestDto;
import com.gyl.CrudGyl.dto.request.RegistroRequestDto;
import com.gyl.CrudGyl.dto.response.IniciarSesionResponseDto;
import com.gyl.CrudGyl.dto.response.RegistroResponseDto;
import com.gyl.CrudGyl.servicio.AutenticacionServicio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AutenticacionControlador {
    private final AutenticacionServicio autenticacionServicio;

    public AutenticacionControlador(AutenticacionServicio autenticacionServicio) {
        this.autenticacionServicio = autenticacionServicio;
    }

    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public RegistroResponseDto registrar (@Valid @RequestBody RegistroRequestDto dto) {
        return autenticacionServicio.registrar(dto);
    }

    @PostMapping("/iniciar_sesion")
    public IniciarSesionResponseDto iniciarSesion (@Valid @RequestBody IniciarSesionRequestDto dto) {
        return autenticacionServicio.iniciarSesion(dto);
    }
}