package com.gyl.CrudGyl.mapper;

import com.gyl.CrudGyl.dto.request.ClienteRequestDto;
import com.gyl.CrudGyl.dto.response.ClienteResponseDto;
import com.gyl.CrudGyl.entidad.Cliente;

public class ClienteMapper {
    public ClienteMapper() {}

    public static Cliente toEntity(ClienteRequestDto dto) {
        Cliente cliente = new Cliente();

        cliente.setNombre(dto.nombre());
        cliente.setApellido(dto.apellido());
        cliente.setCorreo(dto.correo());
        cliente.setTelefono(dto.telefono());
        cliente.setDireccion(dto.direccion());

        return cliente;
    }

    public static ClienteResponseDto toResponseDto (Cliente cliente) {
        return new ClienteResponseDto(
                cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getCorreo(), cliente.getTelefono(),
                cliente.getDireccion()
        );
    }

    public static void actualizarEntidad(Cliente cliente, ClienteRequestDto dto) {
        cliente.setNombre(dto.nombre());
        cliente.setApellido(dto.apellido());
        cliente.setCorreo(dto.correo());
        cliente.setTelefono(dto.telefono());
        cliente.setDireccion(dto.direccion());
    }
}