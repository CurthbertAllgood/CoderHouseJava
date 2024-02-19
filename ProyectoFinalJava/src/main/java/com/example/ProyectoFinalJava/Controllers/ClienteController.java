package com.example.ProyectoFinalJava.Controllers;


import com.example.ProyectoFinalJava.Models.Cliente;
import com.example.ProyectoFinalJava.Services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Get Clients", description = "Permite obtener todos los clientes.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operación exitosa"),})
    @GetMapping("/all")
    public List<Cliente> getClients() {
        return clienteService.getClientes();
    }

    @Operation(summary = "Get Client by ID", description = "Permite obtener un cliente por ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operación exitosa"), @ApiResponse(responseCode = "404", description = "Cliente no encontrado")})
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClientById(@PathVariable Long id) {
        Optional<Cliente> foundClient = clienteService.getClienteById(id);
        return foundClient.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create Client", description = "Permite crear un nuevo cliente.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operación exitosa")})
    @PostMapping("/add")
    public ResponseEntity<Cliente> createClient(@RequestBody Cliente cliente) {
        Cliente createdClient = clienteService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }


    @Operation(summary = "Update Client by ID", description = "Permite actualizar un cliente por ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operación exitosa"), @ApiResponse(responseCode = "404", description = "Cliente no encontrado")})
    @PutMapping("/update/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> updatedClient = Optional.ofNullable(clienteService.updateClient(id, cliente));
        return updatedClient.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete Client by ID", description = "Permite eliminar un cliente por ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operación exitosa"), @ApiResponse(responseCode = "404", description = "Cliente no encontrado")})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Long id) {
        Optional<Cliente> deletedClient = Optional.ofNullable(clienteService.deleteClient(id));
        return deletedClient.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}