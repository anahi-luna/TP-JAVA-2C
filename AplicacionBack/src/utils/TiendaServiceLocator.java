package utils;

import java.util.HashMap;
import java.util.Map;

import interfaces.Servicio;

public class TiendaServiceLocator {
	private final Map<String, Servicio> servicios = new HashMap<>();
	
	public void registrar(String nombre, Servicio servicio) {
		servicios.put(nombre.toUpperCase(), servicio);
	}
	
	public Servicio get(String nombre) {
		return servicios.get(nombre.toUpperCase());
	}
}
