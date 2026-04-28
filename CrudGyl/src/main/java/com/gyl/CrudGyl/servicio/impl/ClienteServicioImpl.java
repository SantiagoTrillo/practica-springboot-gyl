package com.gyl.CrudGyl.servicio.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gyl.CrudGyl.dto.ClienteRequestDto;
import com.gyl.CrudGyl.dto.ClienteResponseDto;
import com.gyl.CrudGyl.entidad.Cliente;
import com.gyl.CrudGyl.excepcion.ExcepcionRecursoNoEncontrado;
import com.gyl.CrudGyl.mapper.ClienteMapper;
import com.gyl.CrudGyl.repositorio.ClienteRepositorio;
import com.gyl.CrudGyl.servicio.ClienteServicio;

@Service
public class ClienteServicioImpl implements ClienteServicio {
    private final ClienteRepositorio clienteRepositorio;

    public ClienteServicioImpl(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    public ClienteResponseDto crear(ClienteRequestDto dto) {
        Cliente clienteTraducido = ClienteMapper.toEntity(dto);
        Cliente clienteGuardado = clienteRepositorio.save(clienteTraducido);

        return ClienteMapper.toResponseDto(clienteGuardado);
    }

    @Override
    public List<ClienteResponseDto> listar() {
        return clienteRepositorio.findAll().stream().map(ClienteMapper::toResponseDto).toList();
    }

    @Override
    public ClienteResponseDto buscarPorId(Long idBuscado) {
        return clienteRepositorio.findById(idBuscado).map(ClienteMapper::toResponseDto)
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("No se encontró el id " + idBuscado));
    }

    @Override
    public List<ClienteResponseDto> buscarPorNombre(String nombreBuscado) {
        return clienteRepositorio.findByNombre(nombreBuscado).stream().map(ClienteMapper::toResponseDto).toList();
    }

    @Override
    public ClienteResponseDto actualizar(Long idBuscado, ClienteRequestDto dto) {
        Cliente clienteBuscado = clienteRepositorio.findById(idBuscado)
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("No se encontró el id " + idBuscado));
        ClienteMapper.actualizarEntidad(clienteBuscado, dto);
        Cliente clienteGuardado = clienteRepositorio.save(clienteBuscado);

        return ClienteMapper.toResponseDto(clienteGuardado);
    }

    @Override
    public void eliminar(Long idBuscado) {
        Cliente clienteBuscado = clienteRepositorio.findById(idBuscado)
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("No se encontró el id " + idBuscado));
        clienteRepositorio.delete(clienteBuscado);
    }
}