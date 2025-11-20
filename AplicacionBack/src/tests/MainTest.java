package tests;

import java.util.List;
import java.util.Scanner;

import container.ArticuloContenedor;
import exceptions.ValidadorException;
import models.Articulo;
import parsers.ComandoParser;
import parsers.Solicitud;
import services.ArticuloServicio;
import utils.TiendaServiceLocator;
import validation.ArticuloValidador;
import validation.ValidadorNumero;
import validation.ValidadorString;

public class MainTest {

	public static void main(String[] args) {
		/* //TEST CONTENEDOR
		ArticuloContenedor cont = new ArticuloContenedor();	
	
		Articulo a1 = new Articulo("A1","Lapicera",2000.0,5);
		Articulo a2 = new Articulo("B1","Cuaderno",3000.0,4);
		
		//crear articulo
		cont.crear(a1);
		cont.crear(a2);
		//Traer todos
		System.out.println("=== LISTA INICIAL ===");
		cont.getTodos().forEach(System.out::println);
		
		//editar articulo
		Articulo artEditado = new Articulo("A1", "Lapiz negro HB", 120, 60);
		boolean editado = cont.editar(artEditado);
		System.out.println("\n¿Se editó A1?: " + editado);
		
		System.out.println("\n=== LISTA TRAS EDITAR ===");
        cont.getTodos().forEach(System.out::println);
        
        //Buscar articulo por codigo
		Articulo buscado = cont.buscar("A1");
		System.out.println("Buscado: "+buscado);
		
		//Eliminar art por codigo
		boolean eliminado = cont.eliminar("B1");
        System.out.println("\n¿Se eliminó A2?: " + eliminado);
		
        System.out.println("\n=== LISTA FINAL ===");
        cont.getTodos().forEach(System.out::println); */
		
		/*//TEST VALIDADOR
		
		//ARTICULO-VALIDADOR
		try {
			Articulo a = new Articulo("A1", "Lapicera",2, -3);
			ArticuloValidador.validar(a);
			System.out.println("Validado ok!");
		}catch (ValidadorException e) {
			System.out.println("Error: "+ e.getMessage());
		}
		*/
	
		
		
		ArticuloContenedor cont = new ArticuloContenedor();
        ArticuloServicio svc = new ArticuloServicio(cont);
        
        TiendaServiceLocator locator = new TiendaServiceLocator();
        
        locator.registrar("ARTICULO", svc);
        
        try (Scanner sc = new Scanner(System.in)){
        	System.out.println("Escribí comandos (ej: articulos/agregar?codigo=A1&descripcion=Lapiz&precio=120&stock=10)");
            System.out.println("Salir: exit");
            
            while(true) {
            	System.out.print("> ");
            	String linea = sc.nextLine();
            	
                if ("exit".equalsIgnoreCase(linea)) break;
                
                Solicitud soli = ComandoParser.parse(linea);
                if (soli == null) {
                    System.out.println("Comando inválido");
                    continue;
                }
                
             // obtener servicio por recurso
                Object serv = locator.get(soli.getRecurso().toUpperCase());
                if (serv == null) {
                    System.out.println("No existe servicio para: " + soli.getRecurso());
                    continue;
                }
                
             // ejecutar
                Object resultado = ((ArticuloServicio)serv).ejecutar(soli);
                if (resultado instanceof List) {
                    ((List<?>) resultado).forEach(System.out::println);
                } else {
                    System.out.println(resultado);
                }
            }
        }
        
        
	}

}
