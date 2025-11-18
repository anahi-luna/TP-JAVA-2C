package container;

import java.util.ArrayList;
import java.util.List;

public abstract class Contenedor<T> {
	protected final List<T> listado;
	
	public Contenedor() {
		this.listado= new ArrayList<>();
	}
	
	//Cada subclase define la clave única del elemento (ej: codigo, username).
	protected abstract String extraerClave(T elemento);
	
	//Agregar un solo elemento
		public void  agregar(T elemento) {
			if(elemento == null ) {
				throw new IllegalArgumentException("El elemento no puede ser null");
			}
		
			String clave = extraerClave(elemento);
			if(clave == null || clave.trim().isEmpty()) {
				throw new IllegalArgumentException("La clave no puede estar vacía");
			}
			
			if(this.existe(clave)) {
				throw new RuntimeException("Ya existe un elemento con la clave:"+clave);
			}
			
			listado.add(elemento);
		}
		
		// Buscar por clave
		public T buscar(String claveBuscada) {
			if(claveBuscada == null ) return null;
			for(T elemento : listado) {
				String claveElem = extraerClave(elemento);
				if(claveBuscada.equals(claveElem)) {
					return elemento;
				}
			}
			return null;
		}
		
		// Devuelve copia de la lista para evitar exposición directa
		public List<T> getTodos(){
			return new ArrayList<>(listado);
		}
		
		// Verifica existencia por clave
		public boolean existe(String clave) {
			return buscar(clave) != null;
		}
}
