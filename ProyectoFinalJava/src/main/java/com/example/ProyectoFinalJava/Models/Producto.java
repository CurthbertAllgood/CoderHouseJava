package com.example.ProyectoFinalJava.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto; // Identificador único del producto

    private String nombre; // Nombre del producto
    private double precio; // Precio del producto
    private int stock; // Cantidad disponible en stock del producto
}
