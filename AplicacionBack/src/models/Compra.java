package models;

import java.time.LocalDateTime;
import java.util.List;

public class Compra {

	private String id;
    private String username; // comprador
    private LocalDateTime fecha;
    private List<CompraItem> items;
    private double total;

    public Compra(String id, String username, LocalDateTime fecha, List<CompraItem> items) {
        this.id = id;
        this.username = username;
        this.fecha = fecha;
        this.items = items;
        this.total = items.stream().mapToDouble(CompraItem::getSubtotal).sum();
    }

    public String getId() { return id; }
    public String getUsername() { return username; }
    public LocalDateTime getFecha() { return fecha; }
    public List<CompraItem> getItems() { return items; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        return "Compra#" + id + " user=" + username + " total=" + total + " items=" + items.size();
    }
	
	
}
