package models;

import java.time.LocalDateTime;
import java.util.List;

public class Compra {

	private int id;
	private Usuario usuario; 
	private List<CompraItem> items;
	private double montoFinal;
	private LocalDateTime fechaCompra; 
}
