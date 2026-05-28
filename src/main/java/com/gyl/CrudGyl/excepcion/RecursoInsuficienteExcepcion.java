package com.gyl.CrudGyl.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RecursoInsuficienteExcepcion extends RuntimeException {
    public RecursoInsuficienteExcepcion(String message) {super(message);}
}