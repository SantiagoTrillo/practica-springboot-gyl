package com.gyl.CrudGyl.controlador;

import com.gyl.CrudGyl.dto.request.TipoProductoRequestDto;
import com.gyl.CrudGyl.dto.response.TipoProductoResponseDto;
import com.gyl.CrudGyl.servicio.TipoProductoServicio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos_producto")
public class TipoProductoControlador {
    private final TipoProductoServicio tipoProductoServicio;

    public TipoProductoControlador(TipoProductoServicio tipoProductoServicio) {
        this.tipoProductoServicio = tipoProductoServicio;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoProductoResponseDto crear(@Valid @RequestBody TipoProductoRequestDto dto) {
        return tipoProductoServicio.crear(dto);
    }

    @GetMapping
    public List<TipoProductoResponseDto> listar() {return tipoProductoServicio.listar();}

    @GetMapping("/{idBuscado}")
    public TipoProductoResponseDto buscarPorId(@PathVariable Long idBuscado) {
        return tipoProductoServicio.buscarPorId(idBuscado);
    }

    @GetMapping("/nombre")
    public List<TipoProductoResponseDto> buscarPorNombre(@RequestParam String nombre) {
        return tipoProductoServicio.buscarPorNombre(nombre);
    }

    @PutMapping("/{idBuscado}")
    public TipoProductoResponseDto actualizar(@PathVariable Long idBuscado,
                                              @Valid @RequestBody TipoProductoRequestDto dto) {
        return tipoProductoServicio.actualizar(idBuscado, dto);
    }

    @DeleteMapping("/{idBuscado}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long idBuscado) {tipoProductoServicio.eliminar(idBuscado);}
}