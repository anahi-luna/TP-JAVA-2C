package models;

import java.time.LocalDateTime;
import java.util.List;

import javax.print.DocFlavor.STRING;

import enums.EstadoDelCarrito;

public class Carrito {

	private int id;
	private Usuario usuario;
	private List<CarritoItem> items;
	private EstadoDelCarrito estado; 
	
	public Carrito(int id, Usuario usuario, List<CarritoItem> items, EstadoDelCarrito estado) {
		this.id = id;
		this.usuario = usuario; 
		this.items = items;
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public List<CarritoItem> getItems() {
		return items;
	}

	public void setItems(List<CarritoItem> items) {
		this.items = items;
	}

	public EstadoDelCarrito getEstado() {
		return estado;
	}

	public void setEstado(EstadoDelCarrito estado) {
		this.estado = estado;
	}

	public int getId() {
		return id;
	}
	
	//para calcular monto total
	public double getMontoFinal(){
		double total=0;
		for (CarritoItem item : items) {
			total += item.getSubtotal();
		}
	
		return total; 
	}
	
	
	
		

}
