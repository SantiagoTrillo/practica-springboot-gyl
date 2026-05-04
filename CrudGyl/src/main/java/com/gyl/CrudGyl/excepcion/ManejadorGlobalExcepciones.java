package com.gyl.CrudGyl.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarMethodArgumentNotValid(MethodArgumentNotValidException excepcion) {
        Map <String, String> errores = new HashMap<>();

        for (FieldError error : excepcion.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }

        Map <String, Object> respuesta = new HashMap<>();

        respuesta.put("Error", "Error de validación en el cuerpo de la solicitud");
        respuesta.put("Mensaje", excepcion.getMessage());
        respuesta.put("Detalles", errores);
        respuesta.put("Estado", HttpStatus.BAD_REQUEST.value());
        respuesta.put("Fecha", LocalDateTime.now());

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> manejarHttpMessageNotReadable(HttpMessageNotReadableException excepcion) {
        Map <String, Object> respuesta = new HashMap<>();

        respuesta.put("Error", "El cuerpo JSON es inválido o tiene formato incorrecto");
        respuesta.put("Mensaje", excepcion.getMessage());
        respuesta.put("Estado", HttpStatus.BAD_REQUEST.value());
        respuesta.put("Fecha", LocalDateTime.now());

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> manejarMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException excepcion) {
        Map <String, Object> respuesta = new HashMap<>();

        respuesta.put("Error", "Parámetro con tipo inválido");
        respuesta.put("Mensaje", excepcion.getMessage());
        respuesta.put("Estado", HttpStatus.BAD_REQUEST.value());
        respuesta.put("Fecha", LocalDateTime.now());

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, Object>> manejarMissingServletRequestParameter(MissingServletRequestParameterException excepcion) {
        Map <String, Object> respuesta = new HashMap<>();

        respuesta.put("Error", "Falta un parámetro obligatorio en la URL");
        respuesta.put("Mensaje", excepcion.getMessage());
        respuesta.put("Estado", HttpStatus.BAD_REQUEST.value());
        respuesta.put("Fecha", LocalDateTime.now());

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> manejarHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException excepcion) {
        Map <String, Object> respuesta = new HashMap<>();

        respuesta.put("Error", "Método HTTP no permitido para este endpoint");
        respuesta.put("Mensaje", excepcion.getMessage());
        respuesta.put("Estado", HttpStatus.METHOD_NOT_ALLOWED.value());
        respuesta.put("Fecha", LocalDateTime.now());

        return new ResponseEntity<>(respuesta, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> manejarHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException excepcion) {
        Map <String, Object> respuesta = new HashMap<>();

        respuesta.put("Error", "Formato de texto no soportado. Usá JSON");
        respuesta.put("Mensaje", excepcion.getMessage());
        respuesta.put("Estado", HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        respuesta.put("Fecha", LocalDateTime.now());

        return new ResponseEntity<>(respuesta, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> manejarNoHandlerFound(NoHandlerFoundException  excepcion) {
        Map <String, Object> respuesta = new HashMap<>();

        respuesta.put("Error", "Endpoint no encontrado");
        respuesta.put("Mensaje", excepcion.getMessage());
        respuesta.put("Estado", HttpStatus.NOT_FOUND.value());
        respuesta.put("Fecha", LocalDateTime.now());

        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
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

        respuesta.put("Error", "El recurso está en un estado que impide la operación");
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