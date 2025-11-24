package models;

public class CarritoItem {

	private String articuloCodigo; // guardamos código para identificar
    private Articulo articulo;     // referencia (puede ser null si se prefiere solo codigo)
    private int cantidad;
	
    public CarritoItem(String articuloCodigo, Articulo articulo, int cantidad) {
		this.articuloCodigo = articuloCodigo;
		this.articulo = articulo;
		this.cantidad = cantidad;
	}
	
    public String getArticuloCodigo() { return articuloCodigo; }
    public Articulo getArticulo() { return articulo; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getSubtotal() {
        return (articulo != null) ? articulo.getPrecio() * cantidad : 0.0;
    }

    @Override
    public String toString() {
        String desc = (articulo != null) ? articulo.getDescripcion() : "N/D";
        return articuloCodigo + " | " + desc + " | cant=" + cantidad + " | subtotal=" + getSubtotal();
    }
	
	
}
