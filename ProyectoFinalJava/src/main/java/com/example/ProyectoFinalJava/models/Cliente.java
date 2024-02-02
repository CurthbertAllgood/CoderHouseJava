package com.example.ProyectoFinalJava.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cliente")
    private Long id;

    @Column(name="nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name= "fechaNacimiento")
    private LocalDate fechaNacimiento;

    public int getEdad(){
        LocalDate now= LocalDate.now();
        return Period.between(this.fechaNacimiento, now).getYears();

    }
}
