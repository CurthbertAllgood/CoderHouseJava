package com.example.ProyectoFinalJava.Controllers;


import com.example.ProyectoFinalJava.Models.Venta;
import com.example.ProyectoFinalJava.Models.VentaRequest;
import com.example.ProyectoFinalJava.Services.VentaService;
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
@RequestMapping("/ventas")
public class VentasController {

    @Autowired
    private VentaService ventaService;


    @Operation(summary = "Get Sales", description = "Permite obtener todos las ventas.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operación exitosa"),})
    @GetMapping("/all")
    public List<Venta> getAll() {
        return ventaService.getAllVentas();
    }

    @Operation(summary = "Get Sale by ID", description = "Permite obtener una venta por ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operación exitosa"), @ApiResponse(responseCode = "404", description = "Venta no encontrada")})
    @GetMapping("/{id}")
    public ResponseEntity<Venta> getSaleById(@PathVariable Long id) {
        Optional<Venta> foundSale = ventaService.getVentaById(id);
        return foundSale.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create Sale", description = "Permite crear una nuevo venta.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operación exitosa")})
    @PostMapping("/add")
    public ResponseEntity<Venta> addSale(@RequestBody VentaRequest ventaRequest) {
        Venta createdSale = ventaService.crearVenta(ventaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSale);
    }

    @Operation(summary = "Generate Invoice by ID", description = "Permite generar un ticket por ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operación exitosa"), @ApiResponse(responseCode = "404", description = "Venta no encontrada")})
    @GetMapping("/invoice/{id}")
    public String generateInvoice(@PathVariable Long id) {
        return ventaService.generateInvoice(id);
    }

    @Operation(summary = "Delete Sale by ID", description = "Permite eliminar una venta por ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operación exitosa"), @ApiResponse(responseCode = "404", description = "Venta no encontrado")})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Venta> deleteSale(@PathVariable Long id) {
        Optional<Venta> deletedSale = Optional.ofNullable(ventaService.deleteSale(id));
        return deletedSale.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}