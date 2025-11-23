package models;

import java.util.List;

public class Compra {

	private int id;
	private Usuario usuario; 
	private Carrito carrito; 
	private List<CompraItem> items;

	public Compra(int id, Usuario usuario, Carrito carrito, List<CompraItem> items) {
		this.id = id;
		this.usuario = usuario;
		this.carrito = carrito;
		this.items = items;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public List<CompraItem> getItems() {
		return items;
	}

	public void setItems(List<CompraItem> items) {
		this.items = items;
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
