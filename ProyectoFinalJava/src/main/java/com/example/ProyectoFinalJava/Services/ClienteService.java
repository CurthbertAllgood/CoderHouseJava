package com.example.ProyectoFinalJava.Services;

import com.example.ProyectoFinalJava.Models.Cliente;
import com.example.ProyectoFinalJava.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clientRepository;

    // Método para obtener todos los clientes
    public List<Cliente> getClientes() {
        return clientRepository.findAll();
    }

    // Método para obtener un cliente por su ID
    public Optional<Cliente> getClienteById(Long id) {
        return clientRepository.findById(id);
    }

    // Método para crear un nuevo cliente
    public Cliente createCliente(Cliente client) {
        return clientRepository.save(client);
    }

    // Método para actualizar un cliente existente
    public Cliente updateClient(Long id, Cliente client) {
        Optional<Cliente> foundClient = clientRepository.findById(id);
        if (foundClient.isEmpty()) {
            return null; // Devolver null si el cliente no se encuentra
        } else {
            // Actualizar los campos del cliente encontrado y guardarlo en la base de datos
            foundClient.get().setNombre(client.getNombre());
            foundClient.get().setEmail(client.getEmail());
            return clientRepository.save(foundClient.get());
        }
    }

    // Método para eliminar un cliente por su ID
    public Cliente deleteClient(Long id) {
        Optional<Cliente> foundClient = clientRepository.findById(id);
        if (foundClient.isPresent()) {
            clientRepository.deleteById(id);
            return foundClient.get(); // Devolver el cliente eliminado
        } else {
            return null; // Devolver null si el cliente no se encuentra
        }
    }
}
