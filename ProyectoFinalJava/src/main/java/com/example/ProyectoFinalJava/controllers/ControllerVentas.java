package com.example.ProyectoFinalJava.controllers;// ... (otras importaciones)

import com.example.ProyectoFinalJava.models.Venta;
import com.example.ProyectoFinalJava.models.Cliente;
import com.example.ProyectoFinalJava.models.Producto;
import com.example.ProyectoFinalJava.repository.RepositoryVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerVentas {

    @Autowired
    private RepositoryVenta repo;


    @GetMapping("ventas")
    public List<Venta> getVentas() {
        return repo.findAll();
    }

    @PostMapping("altaVenta")
    public String postVenta(@RequestBody Venta venta) {
        venta.calcularMontoTotal(venta.getPrecioProducto());
        repo.save(venta);
        return "Venta registrada";
    }

    @PutMapping("modificarVenta/{id}")
    public String update(@PathVariable Long id, @RequestBody Venta venta) {
        Venta updatedVenta = repo.findById(id).orElse(null);

        if (updatedVenta != null) {
            // Actualiza los campos necesarios
            //updatedVenta.setIdCliente(venta.getIdCliente());
            //updatedVenta.setIdProducto(.getIdProducto());
            updatedVenta.setCantidad(venta.getCantidad());

            updatedVenta.calcularMontoTotal(updatedVenta.getPrecioProducto());

            repo.save(updatedVenta);
            return "Venta modificada";
        } else {
            return "Venta no encontrada";
        }
    }

    @DeleteMapping("bajaVenta/{id}")
    public String delete(@PathVariable Long id) {
        Venta deleteVenta = repo.findById(id).orElse(null);

        if (deleteVenta != null) {
            repo.delete(deleteVenta);
            return "Venta eliminada";
        } else {
            return "Venta no encontrada";
        }
    }
}
