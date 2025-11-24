package models;

public class CompraItem {

	private String articuloCodigo;
    private String descripcion;
    private int cantidad;
    private double precioUnitario;

    public CompraItem(String articuloCodigo, String descripcion, int cantidad, double precioUnitario) {
        this.articuloCodigo = articuloCodigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getArticuloCodigo() { return articuloCodigo; }
    public String getDescripcion() { return descripcion; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }

    public double getSubtotal() { return cantidad * precioUnitario; }

    @Override
    public String toString() {
        return articuloCodigo + " | " + descripcion + " | cant=" + cantidad + " | pu=" + precioUnitario;
    }
}
