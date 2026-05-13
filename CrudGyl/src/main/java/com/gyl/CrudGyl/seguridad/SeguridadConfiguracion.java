package com.gyl.CrudGyl.seguridad;

import com.gyl.CrudGyl.excepcion.ManejadorGlobalExcepciones;
import com.gyl.CrudGyl.servicio.impl.DetallesUsuarioServicioImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SeguridadConfiguracion {
    private final TokenAutenticacionFiltro tokenAutenticacionFiltro;
    private final DetallesUsuarioServicioImpl detallesUsuarioServicio;
    private final ManejadorGlobalExcepciones manejadorGlobalExcepciones;
    private final ObjectMapper objetoMapper;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest ->
                        authRequest.requestMatchers("/api/iniciar_sesion/**", "/api/registrar").permitAll()
                                .anyRequest().authenticated()
                ).exceptionHandling(exceptionManager ->
                        exceptionManager.authenticationEntryPoint((
                                request, response, authException
                        ) -> {
                            var respuesta = manejadorGlobalExcepciones.manejarAuthenticationEntryPoint(authException);
                            response.setStatus(respuesta.getStatusCode().value());
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(objetoMapper.writeValueAsString(respuesta.getBody()));
                        })
                ).sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        tokenAutenticacionFiltro, UsernamePasswordAuthenticationFilter.class
                ).build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuracion = new CorsConfiguration();
        configuracion.setAllowedOrigins(List.of("http://localhost:8080"));
        configuracion.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuracion.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuracion.setExposedHeaders(List.of("Authorization"));
        configuracion.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource fuente = new UrlBasedCorsConfigurationSource();
        fuente.registerCorsConfiguration("/**", configuracion);
        return fuente;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuracion) throws Exception {
        return configuracion.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider proveedorAutenticacion = new DaoAuthenticationProvider(detallesUsuarioServicio);
        proveedorAutenticacion.setPasswordEncoder(passwordEncoder());
        return proveedorAutenticacion;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}