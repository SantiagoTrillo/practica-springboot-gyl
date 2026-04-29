package com.gyl.CrudGyl.repositorio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gyl.CrudGyl.entidad.Venta;

@Repository
public interface VentaRepositorio extends JpaRepository<Venta, Long> {
    List<Venta> findByFechaVenta(LocalDateTime fechaVentaBuscada);
}