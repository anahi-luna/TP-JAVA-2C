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
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaModificacion;
	
	public Carrito(int id, List<CarritoItem> items, EstadoDelCarrito estado,
			LocalDateTime fechaCreacion, LocalDateTime fechaModificacion) {
		this.id = id;
		this.items = items;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
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

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
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
