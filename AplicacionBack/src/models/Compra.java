package models;

import java.time.LocalDateTime;
import java.util.List;

public class Compra {

	private int id;
	private Usuario usuario; 
	private List<CompraItem> items;
	private LocalDateTime fechaCompra;
	
	public Compra(int id, Usuario usuario, List<CompraItem> items, LocalDateTime fechaCompra) {
		this.id = id;
		this.usuario = usuario;
		this.items = items;
		this.fechaCompra = fechaCompra;
	}

	public List<CompraItem> getItems() {
		return items;
	}

	public void setItems(List<CompraItem> items) {
		this.items = items;
	}

	public LocalDateTime getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDateTime fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public int getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	} 
	
	//total queda como parte del historial
	public double getMontoFinal(){
		double total=0;
		for (CompraItem item : items) {
			total += item.getSubtotal();
		}
	
		return total; 
	}
	
	
}
