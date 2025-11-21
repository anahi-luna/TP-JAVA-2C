package models;

public class CompraItem {

	private int id;
	private Articulo articulo;
	private int cantidad;
	private double precioUnitario; 
	
	public CompraItem(int id, Articulo articulo, int cantidad, double precioUnitario) {
		this.id = id;
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}
	
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public double getSubtotal () {
		return cantidad  * precioUnitario; 
	}
}
