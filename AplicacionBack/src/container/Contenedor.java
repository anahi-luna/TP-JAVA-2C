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
		public void  crear(T elemento) {
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
		
		// Eliminar por clave
		public boolean eliminar(String clave) {
	        if (clave == null) return false;

	        for (int i = 0; i < listado.size(); i++) {
	            T elemento = listado.get(i);
	            String claveElem = extraerClave(elemento);

	            if (clave.equals(claveElem)) {
	                listado.remove(i);
	                return true; 
	            }
	        }

	        return false;
	    }
		
		//Editar elemento
		public boolean editar(T elementoActualizado) {
		    if (elementoActualizado == null) {
		        throw new IllegalArgumentException("El elemento no puede ser null");
		    }

		    String clave = extraerClave(elementoActualizado);
		    if (clave == null || clave.trim().isEmpty()) {
		        throw new IllegalArgumentException("La clave no puede estar vacía");
		    }

		    for (int i = 0; i < listado.size(); i++) {
		        T existente = listado.get(i);
		        if (clave.equals(extraerClave(existente))) {
		            listado.set(i, elementoActualizado); // reemplazo completo
		            return true;
		        }
		    }

		    return false;
		}

}
