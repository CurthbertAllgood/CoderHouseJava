package com.example.ProyectoFinalJava.Services;

import com.example.ProyectoFinalJava.Models.*;
import com.example.ProyectoFinalJava.Repository.VentaRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProductoService productoService;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    // Constructor de la clase VentaService
    public VentaService() {
        // Inicialización del cliente HTTP y el ObjectMapper
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    // Método para obtener todas las ventas
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    // Método para obtener una venta por su ID
    public Optional<Venta> getVentaById(Long id) {
        return ventaRepository.findById(id);
    }



    // Método para crear una nueva venta
    public Venta crearVenta(VentaRequest ventaRequest) {
        // Obtener cliente asociado a la venta
        Optional<Cliente> foundClient = clienteService.getClienteById(ventaRequest.getAllClientes());
        if (foundClient.isEmpty()) {
            return null;
        }

        // Obtener productos asociados a la venta
        List<Long> productIds = ventaRequest.getAllProductos();
        List<Producto> foundProducts = productoService.getProductosById(productIds);
        if (foundProducts.isEmpty()) {
            return null;
        }

        // Inicialización de la lista de productos de la venta y el mensaje de productos omitidos
        List<Producto> ventaProductos = new ArrayList<>();
        StringBuilder mensajeOmitidos = new StringBuilder();

        // Iterar sobre los productos encontrados
        for (Producto product : foundProducts) {
            // Verificar si hay suficiente stock para agregar el producto a la venta
            if (product.getStock() >= 1 && productIds.contains(product.getId_producto())) {
                ventaProductos.add(product);
                productoService.actualizarStock(product.getId_producto(), 1); // Actualizar stock del producto
            } else {
                // Agregar mensaje de producto omitido debido a falta de stock
                mensajeOmitidos.append("Producto con ID ")
                        .append(product.getId_producto())
                        .append(" omitido debido a la falta de stock.\n");
            }
        }

        // Calcular el total de la venta
        double saleTotal = ventaProductos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();

        // Obtener fecha y hora actual
        LocalDateTime ventaFecha = getCurrentDateTime();

        // Crear objeto Venta con los datos obtenidos
        Venta sale = new Venta();
        sale.setId_cliente(foundClient.get());
        sale.setProducts(ventaProductos);
        sale.setFecha_venta(ventaFecha);
        sale.setCantidad(ventaProductos.size());
        sale.setTotal(saleTotal);

        // Agregar mensaje de productos omitidos, si los hay
        if (mensajeOmitidos.length() > 0) {
            sale.setMensajeOmitidos(mensajeOmitidos.toString());
        }

        // Guardar la venta en la base de datos y retornarla
        return ventaRepository.save(sale);
    }

    // Método para generar una factura de una venta
    public String generateInvoice(Long id) {
        // Obtener venta por su ID
        Optional<Venta> foundSale = ventaRepository.findById(id);
        if (foundSale.isEmpty()) {
            return "Venta no encontrada";
        }

        // Generar factura de la venta
        StringBuilder template = new StringBuilder();
        // Aquí se puede implementar la generación de la factura con los datos de la venta
        return template.toString();
    }

    // Método para eliminar una venta por su ID
    public Venta deleteSale(Long id) {
        Optional<Venta> foundSale = ventaRepository.findById(id);
        if (foundSale.isPresent()) {
            ventaRepository.deleteById(id);
            return foundSale.get();
        } else {
            return null;
        }
    }

    // Método para obtener la fecha y hora actual en la zona horaria del sistema
    private LocalDateTime getCurrentDateTime() {
        try {
            // Realizar una solicitud HTTP para obtener la fecha y hora actual en formato UTC
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://worldclockapi.com/api/json/utc/now"))
                    .build();

            // Obtener la respuesta del servidor
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) { // Verificar si la respuesta es exitosa
                // Leer la respuesta y convertirla a un objeto JsonNode
                JsonNode jsonNode = objectMapper.readTree(response.body());
                // Obtener la fecha y hora actual en formato ISO-8601
                String currentDateTimeString = jsonNode.get("currentDateTime").asText();
                // Parsear la fecha y hora a un objeto LocalDateTime
                LocalDateTime currentDateTimeUtc = LocalDateTime.parse(currentDateTimeString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

                // Obtener la zona horaria del sistema
                ZoneId zoneId = ZoneId.systemDefault();
                // Convertir la fecha y hora a la zona horaria del sistema
                ZonedDateTime zonedDateTime = currentDateTimeUtc.atZone(ZoneOffset.UTC).withZoneSameInstant(zoneId);
                // Obtener la fecha y hora en la zona horaria del sistema
                return zonedDateTime.toLocalDateTime();
            } else {
                // En caso de error al obtener la fecha y hora del servidor, imprimir un mensaje de error y devolver la fecha y hora actual
                System.err.println("Error al obtener la fecha y hora del servidor: " + response.statusCode());
                return LocalDateTime.now();
            }
        } catch (Exception e) {
            // En caso de excepción, imprimir el stack trace y devolver la fecha y hora actual
            e.printStackTrace();
            return LocalDateTime.now();
        }
    }
}
