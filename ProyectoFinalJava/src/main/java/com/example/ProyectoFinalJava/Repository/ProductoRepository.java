package com.example.ProyectoFinalJava.Repository;

import com.example.ProyectoFinalJava.Models.Producto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}