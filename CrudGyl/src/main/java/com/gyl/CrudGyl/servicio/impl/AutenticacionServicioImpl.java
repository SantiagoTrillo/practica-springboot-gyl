package com.gyl.CrudGyl.servicio.impl;

import com.gyl.CrudGyl.dto.request.IniciarSesionRequestDto;
import com.gyl.CrudGyl.dto.request.RegistroRequestDto;
import com.gyl.CrudGyl.dto.response.IniciarSesionResponseDto;
import com.gyl.CrudGyl.dto.response.RegistroResponseDto;
import com.gyl.CrudGyl.entidad.Usuario;
import com.gyl.CrudGyl.mapper.UsuarioMapper;
import com.gyl.CrudGyl.repositorio.UsuarioRepositorio;
import com.gyl.CrudGyl.seguridad.TokenServicio;
import com.gyl.CrudGyl.servicio.AutenticacionServicio;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {
    private final UsuarioRepositorio usuarioRepositorio;
    private final TokenServicio tokenServicio;
    private final PasswordEncoder codificadorContraseña;
    private final AuthenticationManager gerenteAutenticacion;

    @Override
    public RegistroResponseDto registrar(RegistroRequestDto dto) {
        String contraseña = codificadorContraseña.encode(dto.contraseña());

        Usuario usuario = UsuarioMapper.toEntity(dto, contraseña);

        usuarioRepositorio.save(usuario);

        return new RegistroResponseDto(usuario.getUsername(), usuario.getPassword());
    }

    @Override
    public IniciarSesionResponseDto iniciarSesion(IniciarSesionRequestDto dto) {
        gerenteAutenticacion.authenticate(
                new UsernamePasswordAuthenticationToken(dto.nombreUsuario(),dto.contraseña())
        );

        UserDetails usuario = usuarioRepositorio.findByNombreUsuario(dto.nombreUsuario()).orElseThrow();

        String token = tokenServicio.getToken(usuario);

        return new IniciarSesionResponseDto(token);
    }
}