package models;

public class Articulo {
	private String codigo;
	private String nombreDescripcion;
	private double precio;
	private int stock;
	
	public Articulo(String codigo, String nombreDescripcion, double precio, int stock) {
		super();
		this.codigo = codigo;
		this.nombreDescripcion = nombreDescripcion;
		this.precio = precio;
		this.stock = stock;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombreDescripcion() {
		return nombreDescripcion;
	}

	public void setNombreDescripcion(String nombreDescripcion) {
		this.nombreDescripcion = nombreDescripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
