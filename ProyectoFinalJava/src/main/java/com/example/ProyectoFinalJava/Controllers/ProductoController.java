package com.example.ProyectoFinalJava.Controllers;

import com.example.ProyectoFinalJava.Models.Cliente;
import com.example.ProyectoFinalJava.Models.Producto;
import com.example.ProyectoFinalJava.Services.ProductoService;

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
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Get Products", description = "Permite obtener todos los productos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
    })
    @GetMapping("/all")
    public List<Producto> getProducts() {
        return productoService.getProductos();
    }

    @Operation(summary = "Get Product by ID", description = "Permite obtener un producto por ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operación exitosa"), @ApiResponse(responseCode = "404", description = "Venta no encontrada")})
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<Producto> foundClient = productoService.getProductoById(id);
        if (foundClient.isPresent()) {
            return ResponseEntity.ok(foundClient.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El ID del producto indicado no existe");
        }
    }


    @Operation(summary = "Create Product", description = "Permite crear un nuevo producto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa")
    })
    @PostMapping("/add")
    public ResponseEntity<Producto> add(@RequestBody Producto product) {
        Producto createdProduct = productoService.crearProducto(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @Operation(summary = "Update Product by ID", description = "Permite actualizar un producto por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, @RequestBody Producto product) {
        Optional<Producto> updatedProduct = productoService.updateProducto(id, product);
        return updatedProduct.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Delete Product by ID", description = "Permite eliminar un producto por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Producto> delete(@PathVariable Long id) {
        Optional<Producto> deletedProduct = Optional.ofNullable(productoService.deleteProduct(id));
        return deletedProduct.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
