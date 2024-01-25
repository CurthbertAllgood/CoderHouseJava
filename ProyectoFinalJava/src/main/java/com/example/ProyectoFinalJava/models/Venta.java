package com.example.ProyectoFinalJava.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_venta;

    @Column(name="nombreCliente")
    private String nombre;

    @Column(name = "nombreProducto")
    private String nombreProducto;

    @Column(name ="precioProducto")
    Long precioProducto;

    @Column(name="totalVenta")
    private double totalVenta;

    private int cantidad;
    public void calcularMontoTotal(double precioProducto) {
        this.totalVenta = precioProducto * cantidad;
    }

}
