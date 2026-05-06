package com.gyl.CrudGyl.servicio.impl;

import com.gyl.CrudGyl.dto.request.DetalleVentaRequestDto;
import com.gyl.CrudGyl.dto.request.VentaRequestDto;
import com.gyl.CrudGyl.dto.response.VentaResponseDto;
import com.gyl.CrudGyl.entidad.Cliente;
import com.gyl.CrudGyl.entidad.DetalleVenta;
import com.gyl.CrudGyl.entidad.Producto;
import com.gyl.CrudGyl.entidad.Venta;
import com.gyl.CrudGyl.excepcion.RecursoDuplicadoExcepcion;
import com.gyl.CrudGyl.excepcion.RecursoInsuficienteExcepcion;
import com.gyl.CrudGyl.excepcion.RecursoNoEncontradoExcepcion;
import com.gyl.CrudGyl.mapper.DetalleVentaMapper;
import com.gyl.CrudGyl.mapper.VentaMapper;
import com.gyl.CrudGyl.repositorio.ClienteRepositorio;
import com.gyl.CrudGyl.repositorio.ProductoRepositorio;
import com.gyl.CrudGyl.repositorio.VentaRepositorio;
import com.gyl.CrudGyl.servicio.VentaServicio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaServicioImpl implements VentaServicio {
    private final VentaRepositorio ventaRepositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final ProductoRepositorio productoRepositorio;

    public VentaServicioImpl(
            VentaRepositorio ventaRepositorio, ClienteRepositorio clienteRepositorio,
            ProductoRepositorio productoRepositorio
    ) {
        this.ventaRepositorio = ventaRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        this.productoRepositorio = productoRepositorio;
    }

    @Override
    @Transactional
    public VentaResponseDto crear(VentaRequestDto dtoVenta) {
        Cliente comprador = obtenerCliente(dtoVenta.idCliente());

        validarProductosUnicos(dtoVenta.detallesVenta());

        Venta ventaTraducida = VentaMapper.toEntity(comprador);

        List<DetalleVenta> detallesVenta = new ArrayList<>();

        for (DetalleVentaRequestDto dtoDetalle : dtoVenta.detallesVenta()) {
            DetalleVenta detalleNuevo = crearDetalleVenta(dtoDetalle, ventaTraducida);
            detallesVenta.add(detalleNuevo);
        }

        Double totalCalculado = calcularTotal(detallesVenta);

        ventaTraducida.setDetallesVenta(detallesVenta);
        ventaTraducida.setTotal(totalCalculado);

        Venta ventaGuardada = ventaRepositorio.saveAndFlush(ventaTraducida);

        return VentaMapper.toResponseDto(ventaGuardada);
    }

    @Override
    public List<VentaResponseDto> listar() {
        return ventaRepositorio.findAll().stream().map(VentaMapper::toResponseDto).toList();
    }

    @Override
    public VentaResponseDto buscarPorId(Long idBuscado) {
        return ventaRepositorio.findById(idBuscado).map(VentaMapper::toResponseDto)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion(
                        "No se encontró la venta con el id " + idBuscado
                ));
    }

    @Override
    public List<VentaResponseDto> buscarPorFechaVenta(LocalDateTime fechaVentaBuscada) {
        LocalDateTime inicioMinuto = fechaVentaBuscada.withSecond(0).withNano(0);
        LocalDateTime finMinuto = inicioMinuto.plusMinutes(1);

        return mapearVentasAResponseDto(obtenerVentas(inicioMinuto, finMinuto));
    }

    @Override
    public List<VentaResponseDto> buscarPorRangoFechaVenta(LocalDateTime inicioRango, LocalDateTime finRango) {
        return mapearVentasAResponseDto(obtenerVentas(inicioRango, finRango));
    }

    private List<Venta> obtenerVentas(LocalDateTime inicioFecha, LocalDateTime finFecha) {
        return ventaRepositorio.findByFechaVentaBetween(inicioFecha, finFecha);
    }

    private List<VentaResponseDto> mapearVentasAResponseDto(List<Venta> ventas) {
        return ventas.stream().map(VentaMapper::toResponseDto).toList();
    }

    private Cliente obtenerCliente(Long idBuscado) {
        return clienteRepositorio.findById(idBuscado)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion(
                        "No se encontró el cliente con el id " + idBuscado
                ));
    }

    private Producto obtenerProducto(Long idBuscado) {
        return productoRepositorio.findById(idBuscado)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion(
                        "No se encontró el producto con el id " + idBuscado
                ));
    }

    private void validarProductosUnicos(List<DetalleVentaRequestDto> detallesVenta) {
        for (int i = 0; i < detallesVenta.size(); i++) {
            Long idIterado = detallesVenta.get(i).idProducto();

            for (int j = i + 1; j < detallesVenta.size(); j++) {
                if (idIterado.equals(detallesVenta.get(j).idProducto())) {
                    throw new RecursoDuplicadoExcepcion(
                            "El producto con id " + idIterado + " está repetido en la venta"
                    );
                }
            }
        }
    }

    private void validarStockDisponible(Producto producto, Integer cantidadSolicitada) {
        if (producto.getStock() < cantidadSolicitada) {
            throw new RecursoInsuficienteExcepcion(
                    "No hay suficiente stock para el producto con el id " + producto.getId()
            );
        }

        descontarStock(producto, cantidadSolicitada);
    }

    private DetalleVenta crearDetalleVenta(DetalleVentaRequestDto dtoDetalle, Venta ventaRecipiente) {
        Producto productoComprado = obtenerProducto(dtoDetalle.idProducto());

        validarStockDisponible(productoComprado, dtoDetalle.cantidadProducto());

        Double subtotal = productoComprado.getPrecio() * dtoDetalle.cantidadProducto();

        return DetalleVentaMapper.toEntity(dtoDetalle, ventaRecipiente, productoComprado, subtotal);
    }

    private Double calcularTotal(List<DetalleVenta> detallesVenta) {
        return detallesVenta.stream().mapToDouble(DetalleVenta::getSubtotal).sum();
    }

    private void descontarStock(Producto productoActualizado, Integer cantidadDescontada) {
        productoActualizado.setStock(productoActualizado.getStock() - cantidadDescontada);
    }
}