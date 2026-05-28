package com.gyl.CrudGyl.repositorio;

import com.gyl.CrudGyl.entidad.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepositorio extends JpaRepository<Venta, Long> {
    List<Venta> findByFechaVentaBetween(LocalDateTime inicioRango, LocalDateTime finRango);
}