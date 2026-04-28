package com.gyl.CrudGyl.servicio;

import java.util.List;

import com.gyl.CrudGyl.dto.ClienteRequestDto;
import com.gyl.CrudGyl.dto.ClienteResponseDto;

public interface ClienteServicio {
    ClienteResponseDto crear(ClienteRequestDto dto);

    List<ClienteResponseDto> listar();

    ClienteResponseDto buscarPorId(Long idBuscado);

    List<ClienteResponseDto> buscarPorNombre(String nombreBuscado);

    ClienteResponseDto actualizar(Long idBuscado, ClienteRequestDto dto);

    void eliminar(Long idBuscado);
}