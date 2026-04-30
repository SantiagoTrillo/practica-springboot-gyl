package com.gyl.CrudGyl.controlador;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.gyl.CrudGyl.dto.VentaRequestDto;
import com.gyl.CrudGyl.dto.VentaResponseDto;
import com.gyl.CrudGyl.servicio.VentaServicio;

@RestController
@RequestMapping("/api/ventas")
public class VentaControlador {
    private final VentaServicio ventaServicio;

    public VentaControlador(VentaServicio ventaServicio) {this.ventaServicio = ventaServicio;}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VentaResponseDto crear(@Valid @RequestBody VentaRequestDto dto) {return ventaServicio.crear(dto);}

    @GetMapping
    public List<VentaResponseDto> listar() {return ventaServicio.listar();}

    @GetMapping("/{idBuscado}")
    public VentaResponseDto buscarPorId(@PathVariable Long idBuscado) {return ventaServicio.buscarPorId(idBuscado);}

    @GetMapping("/fecha")
    public List<VentaResponseDto> buscarPorFechaVenta(@RequestParam("fechaVenta") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime fechaVentaBuscada) {
        return ventaServicio.buscarPorFechaVenta(fechaVentaBuscada);
    }

    @PutMapping("/{idBuscado}")
    public VentaResponseDto actualizar(@PathVariable Long idBuscado, @Valid @RequestBody VentaRequestDto dto) {
        return ventaServicio.actualizar(idBuscado, dto);
    }

    @DeleteMapping("/{idBuscado}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long idBuscado) {ventaServicio.eliminar(idBuscado);}
}