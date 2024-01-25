package com.example.ProyectoFinalJava.controllers;

import com.example.ProyectoFinalJava.models.Cliente;
import com.example.ProyectoFinalJava.models.Producto;
import com.example.ProyectoFinalJava.repository.RepositoryProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerProducto {

    @Autowired
    private RepositoryProducto repo;

    @GetMapping("productos")
    public List<Producto> get(){
        return repo.findAll();
    }

    @PostMapping("altaProducto")
    public String postCliente(@RequestBody Producto producto){
        repo.save(producto);
        return "Cliente guardado";
    }

    @PutMapping("modificarProducto/{id}")
    public String update(@PathVariable Long id, @RequestBody Producto producto){
        Producto updatedProducto = repo.findById(id).get();
        updatedProducto.setNombre(producto.getNombre());
        updatedProducto.setPrecio(producto.getPrecio());
        updatedProducto.setStock(producto.getStock());
        repo.save(updatedProducto);
        return "producto modificado";
    }

    @DeleteMapping("bajaProducto/{id}")
    public String delete(@PathVariable Long id){
        Producto deleteProducto = repo.findById(id).get();
        repo.delete(deleteProducto);
        return "cliente eliminado";
    }

}
