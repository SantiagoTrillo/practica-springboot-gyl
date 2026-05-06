package com.gyl.CrudGyl.excepcion;

import com.gyl.CrudGyl.dto.response.MensajeErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ManejadorGlobalExcepciones {
    private ResponseEntity<MensajeErrorResponseDto> construirError(String error, String mensaje, HttpStatus status) {
        return ResponseEntity.status(status).body(
                new MensajeErrorResponseDto(error, mensaje, status.value(), LocalDateTime.now())
        );
    }

    private ResponseEntity<MensajeErrorResponseDto> construirError(
            String error, String mensaje, HttpStatus status, List<String> detalles
    ) {
        return ResponseEntity.status(status).body(
                new MensajeErrorResponseDto(error, mensaje, status.value(), LocalDateTime.now(), detalles)
        );
    }

    @ExceptionHandler(RecursoNoEncontradoExcepcion.class)
    public ResponseEntity<MensajeErrorResponseDto> manejarNoEncontrado(RecursoNoEncontradoExcepcion excepcion) {
        return construirError("Recurso no encontrado", excepcion.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecursoInsuficienteExcepcion.class)
    public ResponseEntity<MensajeErrorResponseDto> manejarInsuficiente(RecursoInsuficienteExcepcion excepcion) {
        return construirError("Recurso insuficiente", excepcion.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RecursoDuplicadoExcepcion.class)
    public ResponseEntity<MensajeErrorResponseDto> manejarDuplicado(RecursoDuplicadoExcepcion excepcion) {
        return construirError("Recurso duplicado", excepcion.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeErrorResponseDto> manejarMethodArgumentNotValid(
            MethodArgumentNotValidException excepcion
    ) {
        List<String> detalles = excepcion.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage()).toList();

        return construirError(
                "Error de validación", "Uno o más campos tienen valores inválidos",
                HttpStatus.BAD_REQUEST, detalles
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MensajeErrorResponseDto> manejarHttpMessageNotReadable(HttpMessageNotReadableException excepcion) {
        return construirError(
                "El cuerpo JSON es inválido o tiene formato incorrecto", excepcion.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<MensajeErrorResponseDto> manejarMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException excepcion
    ) {
        return construirError("Parámetro con tipo inválido", excepcion.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<MensajeErrorResponseDto> manejarMissingServletRequestParameter(
            MissingServletRequestParameterException excepcion
    ) {
        return construirError(
                "Falta un parámetro obligatorio en la URL", excepcion.getMessage(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<MensajeErrorResponseDto> manejarHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException excepcion
    ) {
        return construirError(
                "Método HTTP no permitido para este endpoint", excepcion.getMessage(),
                HttpStatus.METHOD_NOT_ALLOWED
        );
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<MensajeErrorResponseDto> manejarHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException excepcion
    ) {
        return construirError(
                "Formato de texto no soportado. Usá JSON", excepcion.getMessage(),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE
        );
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<MensajeErrorResponseDto> manejarNoHandlerFound(NoHandlerFoundException excepcion) {
        return construirError("Endpoint no encontrado", excepcion.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MensajeErrorResponseDto> manejarIllegalArgument(IllegalArgumentException excepcion) {
        return construirError("Argumento inválido", excepcion.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<MensajeErrorResponseDto> manejarIllegalState(IllegalStateException excepcion) {
        return construirError(
                "El recurso está en un estado que impide la operación", excepcion.getMessage(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<MensajeErrorResponseDto> manejarNoSuchElement(NoSuchElementException excepcion) {
        return construirError("Recurso inexistente", excepcion.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeErrorResponseDto> manejarGeneral(Exception excepcion) {
        return construirError(
                "Error interno del servidor", excepcion.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}