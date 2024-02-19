package com.example.ProyectoFinalJava.Repository;

import com.example.ProyectoFinalJava.Models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}