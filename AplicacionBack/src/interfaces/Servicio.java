package interfaces;

import parsers.Solicitud;

public interface Servicio {
	/*Procesa la solicitud y devuelve un resultado (String, objeto, List, etc.).*/
	Object ejecutar(Solicitud solicitud);
}
