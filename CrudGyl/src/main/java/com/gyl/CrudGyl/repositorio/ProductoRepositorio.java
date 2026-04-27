package com.gyl.CrudGyl.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gyl.CrudGyl.entidad.Producto;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
    List<Producto> findByNombre(String nombre);
}