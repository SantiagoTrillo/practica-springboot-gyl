package com.gyl.CrudGyl.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ManejadorGlobalExcepciones {
    @ExceptionHandler(RecursoNoEncontradoExcepcion.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(RecursoNoEncontradoExcepcion excepcion) {
        Map <String, Object> respuesta = new HashMap<>();

        respuesta.put("Error", "Recurso no encontrado");
        respuesta.put("Mensaje", excepcion.getMessage());
        respuesta.put("Estado", HttpStatus.NOT_FOUND.value());
        respuesta.put("Fecha", LocalDateTime.now());

        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecursoInsuficienteExcepcion.class)
    public ResponseEntity<Map<String, Object>> manejarInsuficiente(RecursoInsuficienteExcepcion excepcion) {
        Map <String, Object> respuesta = new HashMap<>();

        respuesta.put("Error", "Recurso insuficiente");
        respuesta.put("Mensaje", excepcion.getMessage());
        respuesta.put("Estado", HttpStatus.CONFLICT.value());
        respuesta.put("Fecha", LocalDateTime.now());

        return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RecursoDuplicadoExcepcion.class)
    public ResponseEntity<Map<String, Object>> manejarDuplicado(RecursoDuplicadoExcepcion excepcion) {
        Map <String, Object> respuesta = new HashMap<>();

        respuesta.put("Error", "Recurso duplicado");
        respuesta.put("Mensaje", excepcion.getMessage());
        respuesta.put("Estado", HttpStatus.BAD_REQUEST.value());
        respuesta.put("Fecha", LocalDateTime.now());

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> manejarIllegalArgument(IllegalArgumentException excepcion) {
        Map <String, Object> respuesta = new HashMap<>();

        respuesta.put("Error", "Argumento inválido");
        respuesta.put("Mensaje", excepcion.getMessage());
        respuesta.put("Estado", HttpStatus.BAD_REQUEST.value());
        respuesta.put("Fecha", LocalDateTime.now());

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, Object>> manejarIllegalState(IllegalStateException excepcion) {
        Map <String, Object> respuesta = new HashMap<>();

        respuesta.put("Error", "Estado inválido");
        respuesta.put("Mensaje", excepcion.getMessage());
        respuesta.put("Estado", HttpStatus.CONFLICT.value());
        respuesta.put("Fecha", LocalDateTime.now());

        return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> manejarNoSuchElement(NoSuchElementException excepcion) {
        Map <String, Object> respuesta = new HashMap<>();

        respuesta.put("Error", "Recurso inexistente");
        respuesta.put("Mensaje", excepcion.getMessage());
        respuesta.put("Estado", HttpStatus.NOT_FOUND.value());
        respuesta.put("Fecha", LocalDateTime.now());

        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarGeneral(Exception excepcion) {
        Map <String, Object> respuesta = new HashMap<>();

        respuesta.put("Error", "Error interno del servidor");
        respuesta.put("Mensaje", excepcion.getMessage());
        respuesta.put("Estado", HttpStatus.INTERNAL_SERVER_ERROR.value());
        respuesta.put("Fecha", LocalDateTime.now());

        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}