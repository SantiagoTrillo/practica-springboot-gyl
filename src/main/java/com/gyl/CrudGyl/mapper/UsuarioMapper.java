package com.gyl.CrudGyl.mapper;

import com.gyl.CrudGyl.dto.request.IniciarSesionRequestDto;
import com.gyl.CrudGyl.dto.request.RegistroRequestDto;
import com.gyl.CrudGyl.entidad.Rol;
import com.gyl.CrudGyl.entidad.Usuario;

public class UsuarioMapper {
    public UsuarioMapper() {}

    public static Usuario toEntity(RegistroRequestDto dto, String contraseña) {
        Usuario usuario = new Usuario();

        usuario.setNombreUsuario(dto.nombreUsuario());
        usuario.setContraseña(contraseña);
        usuario.setRol(Rol.USUARIO);

        return usuario;
    }

    public static void actualizarEntidad(Usuario usuario, IniciarSesionRequestDto dto) {
        usuario.setNombreUsuario(dto.nombreUsuario());
        usuario.setContraseña(dto.contraseña());
    }
}