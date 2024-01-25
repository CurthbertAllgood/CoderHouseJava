package com.example.ProyectoFinalJava.repository;


import com.example.ProyectoFinalJava.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryProducto extends JpaRepository<Producto, Long> {
}
