package com.gyl.CrudGyl.excepcion;

import com.gyl.CrudGyl.dto.response.ErrorResponseDto;
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
    private ResponseEntity<ErrorResponseDto> construirError(String error, String mensaje, HttpStatus status) {
        return ResponseEntity.status(status).body(
                new ErrorResponseDto(error, mensaje, status.value(), LocalDateTime.now())
        );
    }

    @ExceptionHandler(RecursoNoEncontradoExcepcion.class)
    public ResponseEntity<ErrorResponseDto> manejarNoEncontrado(RecursoNoEncontradoExcepcion excepcion) {
        return construirError("Recurso no encontrado", excepcion.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecursoInsuficienteExcepcion.class)
    public ResponseEntity<ErrorResponseDto> manejarInsuficiente(RecursoInsuficienteExcepcion excepcion) {
        return construirError("Recurso insuficiente", excepcion.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RecursoDuplicadoExcepcion.class)
    public ResponseEntity<ErrorResponseDto> manejarDuplicado(RecursoDuplicadoExcepcion excepcion) {
        return construirError("Recurso duplicado", excepcion.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponseDto> manejarMethodArgumentNotValid(MethodArgumentNotValidException excepcion) {
//        Map<String, String> errores = new HashMap<>();
//        for (FieldError error : excepcion.getBindingResult().getFieldErrors()) {
//            errores.put(error.getField(), error.getDefaultMessage());
//        }
//
//        return construirError(
//                excepcion.getMessage(),
//                errores
//        );
//    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> manejarHttpMessageNotReadable(HttpMessageNotReadableException excepcion) {
        return construirError(
                "El cuerpo JSON es inválido o tiene formato incorrecto", excepcion.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> manejarMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException excepcion
    ) {
        return construirError("Parámetro con tipo inválido", excepcion.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDto> manejarMissingServletRequestParameter(
            MissingServletRequestParameterException excepcion
    ) {
        return construirError(
                "Falta un parámetro obligatorio en la URL", excepcion.getMessage(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> manejarHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException excepcion
    ) {
        return construirError(
                "Método HTTP no permitido para este endpoint", excepcion.getMessage(),
                HttpStatus.METHOD_NOT_ALLOWED
        );
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> manejarHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException excepcion
    ) {
        return construirError(
                "Formato de texto no soportado. Usá JSON", excepcion.getMessage(),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE
        );
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponseDto> manejarNoHandlerFound(NoHandlerFoundException excepcion) {
        return construirError("Endpoint no encontrado", excepcion.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> manejarIllegalArgument(IllegalArgumentException excepcion) {
        return construirError("Argumento inválido", excepcion.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponseDto> manejarIllegalState(IllegalStateException excepcion) {
        return construirError(
                "El recurso está en un estado que impide la operación", excepcion.getMessage(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseDto> manejarNoSuchElement(NoSuchElementException excepcion) {
        return construirError("Recurso inexistente", excepcion.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> manejarGeneral(Exception excepcion) {
        return construirError(
                "Error interno del servidor", excepcion.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}