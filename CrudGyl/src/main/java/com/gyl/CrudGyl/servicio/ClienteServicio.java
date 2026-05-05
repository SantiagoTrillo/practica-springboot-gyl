package com.gyl.CrudGyl.servicio;

import com.gyl.CrudGyl.dto.request.ClienteRequestDto;
import com.gyl.CrudGyl.dto.response.ClienteResponseDto;

import java.util.List;

public interface ClienteServicio {
    ClienteResponseDto crear(ClienteRequestDto dto);

    List<ClienteResponseDto> listar();

    ClienteResponseDto buscarPorId(Long idBuscado);

    List<ClienteResponseDto> buscarPorNombre(String nombreBuscado);

    ClienteResponseDto actualizar(Long idBuscado, ClienteRequestDto dto);

    void eliminar(Long idBuscado);
}