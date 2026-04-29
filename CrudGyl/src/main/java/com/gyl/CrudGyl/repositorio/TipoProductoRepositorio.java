package com.gyl.CrudGyl.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gyl.CrudGyl.entidad.TipoProducto;

@Repository
public interface TipoProductoRepositorio extends JpaRepository<TipoProducto, Long> {
    List<TipoProducto> findByNombre(String nombreBuscado);
}