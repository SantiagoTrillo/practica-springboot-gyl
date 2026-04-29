package com.gyl.CrudGyl.servicio.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gyl.CrudGyl.dto.VentaRequestDto;
import com.gyl.CrudGyl.dto.VentaResponseDto;
import com.gyl.CrudGyl.entidad.Cliente;
import com.gyl.CrudGyl.entidad.Venta;
import com.gyl.CrudGyl.excepcion.ExcepcionRecursoNoEncontrado;
import com.gyl.CrudGyl.mapper.VentaMapper;
import com.gyl.CrudGyl.repositorio.ClienteRepositorio;
import com.gyl.CrudGyl.repositorio.VentaRepositorio;
import com.gyl.CrudGyl.servicio.VentaServicio;

@Service
public class VentaServicioImpl implements VentaServicio {
    private final VentaRepositorio ventaRepositorio;
    private final ClienteRepositorio clienteRepositorio;

    public VentaServicioImpl(VentaRepositorio ventaRepositorio, ClienteRepositorio clienteRepositorio) {
        this.ventaRepositorio = ventaRepositorio;
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    public VentaResponseDto crear(VentaRequestDto dto) {
        Cliente clienteBuscado = clienteRepositorio.findById(dto.idCliente())
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("No se encontró el id " + dto.idCliente()));
        Venta ventaTraducida = VentaMapper.toEntity(dto, clienteBuscado);
        Venta ventaGuardada = ventaRepositorio.save(ventaTraducida);

        return VentaMapper.toResponseDto(ventaGuardada);
    }

    @Override
    public List<VentaResponseDto> listar() {
        return ventaRepositorio.findAll().stream().map(VentaMapper::toResponseDto).toList();
    }

    @Override
    public VentaResponseDto buscarPorId(Long idBuscado) {
        return ventaRepositorio.findById(idBuscado).map(VentaMapper::toResponseDto)
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("No se encontró el id " + idBuscado));
    }

    @Override
        public List<VentaResponseDto> buscarPorFechaVenta(LocalDateTime fechaVentaBuscada) {
        return ventaRepositorio.findByFechaVenta(fechaVentaBuscada).stream().map(VentaMapper::toResponseDto).toList();
    }

    @Override
    public VentaResponseDto actualizar(Long idBuscado, VentaRequestDto dto) {
        Venta ventaBuscada = ventaRepositorio.findById(idBuscado)
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("No se encontró el id " + idBuscado));
        Cliente clienteBuscado = clienteRepositorio.findById(dto.idCliente())
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("No se encontró el id " + dto.idCliente()));
        VentaMapper.actualizarEntidad(ventaBuscada, dto, clienteBuscado);
        Venta ventaGuardada = ventaRepositorio.save(ventaBuscada);

        return VentaMapper.toResponseDto(ventaGuardada);
    }

    @Override
    public void eliminar(Long idBuscado) {
        Venta ventaBuscada = ventaRepositorio.findById(idBuscado)
                .orElseThrow(() -> new ExcepcionRecursoNoEncontrado("No se encontró el id " + idBuscado));
        ventaRepositorio.delete(ventaBuscada);
    }
}