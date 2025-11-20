package parsers;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class ComandoParser {
	//Ejemplo: "articulos/agregar?codigo=A1&descripcion=Lapiz&precio=120&stock=10"
   
	public static Solicitud parse(String texto) {
		if (texto == null || texto.trim().isEmpty()) return null;
		
		String recursoAccion;
		String query = null;
		
		int qIdx = texto.indexOf('?');
		
		if(qIdx >= 0) {
			recursoAccion = texto.substring(0, qIdx);
            query = texto.substring(qIdx + 1);
		}else {
			recursoAccion = texto;
		}
		
		String[] partes = recursoAccion.split("/");
		String recurso = partes.length > 0 ? partes[0]:"";
		String accion = partes.length > 1 ? partes[1] : "";
		
		Map<String, String> params = new HashMap<>();
		
		if(query != null && !query.trim().isEmpty()) {
			String[] pares = query.split("&");
			for(String par : pares) {
				String[] kv = par.split("=",2);
				String k = URLDecoder.decode(kv[1], StandardCharsets.UTF_8);
				String v = kv.length > 1 ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8) : "";
				params.put(k, v);
			}
		}
		
		return new Solicitud(recurso, accion, params);
	}
	
}
