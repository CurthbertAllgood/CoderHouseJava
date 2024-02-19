package com.example.ProyectoFinalJava.Services;

import com.example.ProyectoFinalJava.Models.Producto;
import com.example.ProyectoFinalJava.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productRepository;

    // Método para obtener todos los productos
    public List<Producto> getProductos() {
        return productRepository.findAll();
    }

    // Método para obtener un producto por su ID
    public Optional<Producto> getProductoById(Long id) {
        return productRepository.findById(id);
    }

    // Método para obtener varios productos por sus IDs
    public List<Producto> getProductosById(List<Long> ids) {
        return productRepository.findAllById(ids);
    }

    // Método para crear un nuevo producto
    public Producto crearProducto(Producto product) {
        return productRepository.save(product);
    }

    // Método para actualizar un producto existente
    public Producto updateProducto(Long id, Producto product) {
        Optional<Producto> foundProduct = productRepository.findById(id);
        if (foundProduct.isEmpty()) {
            return null; // Devolver null si el producto no se encuentra
        } else {
            // Actualizar los campos del producto encontrado y guardarlo en la base de datos
            foundProduct.get().setNombre(product.getNombre());
            foundProduct.get().setPrecio(product.getPrecio());
            foundProduct.get().setStock(product.getStock());
            return productRepository.save(foundProduct.get());
        }
    }

    // Método para eliminar un producto por su ID
    public Producto deleteProduct(Long id) {
        Optional<Producto> foundProduct = productRepository.findById(id);
        if (foundProduct.isPresent()) {
            productRepository.deleteById(id);
            return foundProduct.get(); // Devolver el producto eliminado
        } else {
            return null; // Devolver null si el producto no se encuentra
        }
    }

    // Método para actualizar el stock de un producto
    public Producto actualizarStock(Long id, int cantidad) {
        Optional<Producto> productoOptional = productRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            int stockActual = producto.getStock();
            if (stockActual >= cantidad) {
                // Restar la cantidad especificada del stock actual y guardar el producto actualizado
                producto.setStock(stockActual - cantidad);
                return productRepository.save(producto);
            } else {
                // Devolver null si no hay suficiente stock disponible
                return null;
            }
        } else {
            // Devolver null si el producto no se encuentra
            return null;
        }
    }
}
