package com.example.ProyectoFinalJava.repository;

import com.example.ProyectoFinalJava.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCliente extends JpaRepository<Cliente, Long> {
}
