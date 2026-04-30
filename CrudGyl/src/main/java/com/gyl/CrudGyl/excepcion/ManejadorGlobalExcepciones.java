package com.gyl.CrudGyl.excepcion;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ManejadorGlobalExcepciones {
    @ExceptionHandler(RecursoNoEncontradoExcepcion.class)
    public RespuestaError manejarNoEncontrado(RecursoNoEncontradoExcepcion excepcion, HttpServletRequest request) {
        return construirError(HttpStatus.NOT_FOUND, excepcion.getMessage(), request.getRequestURI(), null);
    }

    @ExceptionHandler(RecursoInsuficienteExcepcion.class)
    public RespuestaError manejarInsuficiente(RecursoInsuficienteExcepcion excepcion, HttpServletRequest request) {
        return construirError(HttpStatus.CONFLICT, excepcion.getMessage(), request.getRequestURI(), null);
    }

    @ExceptionHandler(RecursoDuplicadoExcepcion.class)
    public RespuestaError manejarDuplicado(RecursoDuplicadoExcepcion excepcion, HttpServletRequest request) {
        return construirError(HttpStatus.BAD_REQUEST, excepcion.getMessage(), request.getRequestURI(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RespuestaError manejarValidacion(MethodArgumentNotValidException excepcion, HttpServletRequest request) {
        Map<String, String> errores = new LinkedHashMap<>();
        for (FieldError fieldError : excepcion.getBindingResult().getFieldErrors()) {
            errores.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return construirError(HttpStatus.BAD_REQUEST, "Error de validación en los datos enviados",
                              request.getRequestURI(), errores);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class,
                       ConstraintViolationException.class, IllegalArgumentException.class})
    public RespuestaError manejarSolicitudInvalida(Exception excepcion, HttpServletRequest request) {
        return construirError(HttpStatus.BAD_REQUEST, excepcion.getMessage(), request.getRequestURI(),
                    null);
    }

    @ExceptionHandler(Exception.class)
    public RespuestaError manejarGeneral(Exception excepcion, HttpServletRequest request) {
        return construirError(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error interno inesperado",
                              request.getRequestURI(), null);
    }

    private RespuestaError construirError(HttpStatus estado, String mensaje, String path, Map<String,
                                            String> erroresValidacion) {
        return new RespuestaError(LocalDateTime.now(), estado.value(), estado.getReasonPhrase(), mensaje, path,
                erroresValidacion
        );
    }
}