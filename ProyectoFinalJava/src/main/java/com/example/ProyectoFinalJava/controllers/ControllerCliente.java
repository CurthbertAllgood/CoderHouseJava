package com.example.ProyectoFinalJava.controllers;

import com.example.ProyectoFinalJava.models.Cliente;
import com.example.ProyectoFinalJava.repository.RepositoryCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ControllerCliente {

    @Autowired
    private RepositoryCliente repo;
    @GetMapping
    public String index(){
        return "Conectado";
    }

    @GetMapping("todos")
    public List<Cliente> getClientes() {
        return repo.findAll();
    }

    @PostMapping("altaUsuario")
    public String postCliente(@RequestBody Cliente cliente){
        repo.save(cliente);
        return "Cliente guardado";
    }

    @PutMapping("modificar/{id}")
    public String update(@PathVariable Long id, @RequestBody Cliente cliente){
        Cliente updatedCliente = repo.findById(id).get();
        updatedCliente.setNombre(cliente.getNombre());
        updatedCliente.setEmail(cliente.getEmail());
        repo.save(updatedCliente);
        return "usuario modificado";
    }

    @DeleteMapping("baja/{id}")
        public String delete(@PathVariable Long id){
        Cliente deleteCliente = repo.findById(id).get();
            repo.delete(deleteCliente);
            return "cliente eliminado";
        }



}
