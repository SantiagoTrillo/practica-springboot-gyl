package com.gyl.CrudGyl.repositorio;

import com.gyl.CrudGyl.entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProducto extends JpaRepository<Producto, Long> {

}