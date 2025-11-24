package models;

import java.util.ArrayList;
import java.util.List;

import enums.EstadoDelCarrito;

public class Carrito {

	private String id; 
    private String username; 
    private List<CarritoItem> items;
    private EstadoDelCarrito estado;

    public Carrito(String id, String username) {
        this.id = id;
        this.username = username;
        this.items = new ArrayList<>();
        this.estado = EstadoDelCarrito.PENDIENTE;
    }

    public String getId() { return id; }
    public String getUsername() { return username; }
    public List<CarritoItem> getItems() { return items; }
    public EstadoDelCarrito getEstado() { return estado; }
    public void setEstado(EstadoDelCarrito estado) { this.estado = estado; }

    public double getMontoFinal() {
        return items.stream().mapToDouble(CarritoItem::getSubtotal).sum();
    }

    @Override
    public String toString() {
        return "Carrito{id=" + id + ", user=" + username + ", estado=" + estado + ", total=" + getMontoFinal() + ", items=" + items.size() + "}";
    }

}
