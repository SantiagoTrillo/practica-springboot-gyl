package com.gyl.CrudGyl.repositorio;

import com.gyl.CrudGyl.entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
    List<Producto> findByNombre(String nombreBuscado);
}