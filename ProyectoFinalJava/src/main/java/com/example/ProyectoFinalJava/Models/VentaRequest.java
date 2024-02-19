package com.example.ProyectoFinalJava.Models;


import java.util.List;

public class VentaRequest {
    private Long clientId;
    private List<Long> productIds;
    private int quantity;
    private double total;

    public VentaRequest(Long clientId, List<Long> productIds, int quantity, double total) {
        this.clientId = clientId;
        this.productIds = productIds;
        this.quantity = quantity;
        this.total = total;
    }

    public Long getAllClientes() {
        return clientId;
    }

    public List<Long> getAllProductos() {
        return productIds;
    }

    public int getQty() {
        return quantity;
    }

    public double getMontoTotal() {
        return total;
    }
}