package com.gyl.CrudGyl.repositorio;

import com.gyl.CrudGyl.entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNombre(String nombreBuscado);
}