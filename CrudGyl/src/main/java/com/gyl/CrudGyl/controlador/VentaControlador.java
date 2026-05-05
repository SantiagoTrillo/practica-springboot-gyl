package com.gyl.CrudGyl.controlador;

import com.gyl.CrudGyl.dto.request.VentaRequestDto;
import com.gyl.CrudGyl.dto.response.VentaResponseDto;
import com.gyl.CrudGyl.servicio.VentaServicio;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    public List<VentaResponseDto> buscarPorFechaVenta(
            @RequestParam("fechaVenta") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime fechaVentaBuscada
    ) {
        return ventaServicio.buscarPorFechaVenta(fechaVentaBuscada);
    }

    @GetMapping("/fecha/rango")
    public List<VentaResponseDto> buscarPorRangoFecha(
            @RequestParam("desde") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime inicioRango,
            @RequestParam("hasta") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime finRango
    ) {
        return ventaServicio.buscarPorRangoFechaVenta(inicioRango, finRango);
    }
}