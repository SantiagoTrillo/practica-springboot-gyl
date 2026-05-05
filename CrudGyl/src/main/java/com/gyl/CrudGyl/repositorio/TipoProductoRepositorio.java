package com.gyl.CrudGyl.repositorio;

import com.gyl.CrudGyl.entidad.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoProductoRepositorio extends JpaRepository<TipoProducto, Long> {
    List<TipoProducto> findByNombre(String nombreBuscado);
}