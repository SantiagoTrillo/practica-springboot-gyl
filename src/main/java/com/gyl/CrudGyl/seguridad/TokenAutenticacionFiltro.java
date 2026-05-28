package com.gyl.CrudGyl.seguridad;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenAutenticacionFiltro extends OncePerRequestFilter {
    private final TokenServicio tokenServicio;
    private final UserDetailsService detallesUsuarioServicio;

    public TokenAutenticacionFiltro(TokenServicio tokenService, UserDetailsService userDetailsService) {
        this.tokenServicio = tokenService;
        this.detallesUsuarioServicio = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest peticion, HttpServletResponse respuesta, FilterChain cadenaFiltros
    ) throws ServletException, IOException {
        final String cabeceraAutenticacion = peticion.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.hasText(cabeceraAutenticacion) || !cabeceraAutenticacion.startsWith("Bearer ")) {
            cadenaFiltros.doFilter(peticion, respuesta);
            return;
        }

        final String token = cabeceraAutenticacion.substring(7);
        final String nombreUsuario = tokenServicio.getUsernameFromToken(token);

        if (nombreUsuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails detallesUsuario = detallesUsuarioServicio.loadUserByUsername(nombreUsuario);

            if (tokenServicio.isTokenValid(token, detallesUsuario)) {
                UsernamePasswordAuthenticationToken tokenAutenticado = new UsernamePasswordAuthenticationToken(
                        detallesUsuario, null, detallesUsuario.getAuthorities());

                tokenAutenticado.setDetails(new WebAuthenticationDetailsSource().buildDetails(peticion));

                SecurityContextHolder.getContext().setAuthentication(tokenAutenticado);
            }
        }
        cadenaFiltros.doFilter(peticion, respuesta);
    }
}