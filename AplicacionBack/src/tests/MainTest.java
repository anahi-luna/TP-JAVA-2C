package tests;

import java.util.List;
import java.util.Scanner;

import container.ArticuloContenedor;
import container.CompraContenedor;
import container.UsuarioContenedor;
import exceptions.ValidadorException;
import models.Articulo;
import models.Usuario;
import parsers.ComandoParser;
import parsers.Solicitud;
import services.ArticuloServicio;
import services.CarritoServicio;
import services.CompraServicio;
import services.UsuarioServicio;
import utils.TiendaServiceLocator;
import validation.ArticuloValidador;
import validation.ValidadorNumero;
import validation.ValidadorString;

public class MainTest {

	public static void main(String[] args) {
		
		/* // TEST SERVICE LOCATOR 
		ArticuloContenedor cont = new ArticuloContenedor();
        TiendaServiceLocator locator = new TiendaServiceLocator();
        
        locator.registrar("articulos", new ArticuloServicio(cont));
        
        try (Scanner sc = new Scanner(System.in)){
        	System.out.println("Escribí comandos (ej: articulos/crear?codigo=A1&descripcion=Lapiz&precio=120&stock=10");
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
                
             // OBTENER SERVICIO POR RECURSO
                Object serv = locator.get(soli.getRecurso());
                if (serv == null) {
                    System.out.println("No existe servicio para: " + soli.getRecurso());
                    continue;
                }
                
             // EJECUTAR
                Object resultado = ((ArticuloServicio)serv).ejecutar(soli);
                if (resultado instanceof List) {
                    ((List<?>) resultado).forEach(System.out::println);
                } else {
                    System.out.println(resultado);
                }
            }
        } */
        
		/*//COMANDOS DE PRUEBA 
         * articulos/crear?codigo=A2&descripcion=Cuaderno+Rayado&precio=450&stock=50 //CREAR
         * articulos/crear?codigo=A3&descripcion=Goma&Borra&precio=80&stock=30		 //CREAR
         * articulos/listar															 //TRAER TODOS
         * articulos/buscar?codigo=A1												 //BUSCAR
         * articulos/editar?codigo=A1&descripcion=Lapiz+Negro&precio=150 			 //EDITAR
         * articulos/eliminar?codigo=A3												 //ELIMINAR
         * */
		
		/*UsuarioContenedor cont = new UsuarioContenedor();
		UsuarioServicio svc = new UsuarioServicio(cont);
		TiendaServiceLocator locator = new TiendaServiceLocator();
		locator.registrar("usuarios", svc);
		
		try(Scanner sc = new Scanner(System.in)){
			System.out.println("Comandos ejemplo:");
            System.out.println("usuarios/crear?username=juan&password=1234&tipo=CLIENTE");
            System.out.println("usuarios/login?username=juan&password=1234");
            System.out.println("usuarios/agregarsaldo?username=juan&monto=200");
            System.out.println("usuarios/transferir?origen=juan&destino=clienteA&monto=50");
            System.out.println("Salir: exit");
            
            while (true) {
                System.out.print("> ");
                String linea = sc.nextLine();
                if ("exit".equalsIgnoreCase(linea)) break;
                Solicitud soli = ComandoParser.parse(linea);
                if (soli == null) { System.out.println("Comando inválido"); continue; }

                Object serv = locator.get(soli.getRecurso());
                if (serv == null) { System.out.println("No existe servicio para: " + soli.getRecurso()); continue; }

                Object result = ((UsuarioServicio) serv).ejecutar(soli);
                if (result instanceof List) {
                    ((List<?>) result).forEach(System.out::println);
                } else {
                    System.out.println(result);
                }
            }
		}*/

		/*//COMANDOS DE PRUEBA
			* usuarios/crear?username=admin&password=1234&tipo=EMPLEADO      //CREAR
			* usuarios/crear?username=clienteA&password=passA&tipo=CLIENTE	 //CREAR
			* usuarios/listar												 //TRAER TODOS
			* usuarios/login?username=admin&password=1234					 //LOGIN
			* usuarios/agregarsaldo?username=clienteA&monto=200				 //AGREGAR SALDO
			* usuarios/transferir?origen=clienteA&destino=admin&monto=50 	 //TRANSFERENCIA
			* usuarios/buscar?username=clienteA								 //BUSCAR
			* usuarios/eliminar?username=clienteA							 //ELIMINAR
			* usuarios/listar
			*/

		//CONTENEDORES
		ArticuloContenedor artCont = new ArticuloContenedor();
		UsuarioContenedor userCont = new UsuarioContenedor();
        CompraContenedor compraCont = new CompraContenedor();
        
		//SERVICIOS
		ArticuloServicio artServ = new ArticuloServicio(artCont);
		UsuarioServicio userServ = new UsuarioServicio(userCont);
		CarritoServicio carritoServ = new CarritoServicio(artCont, compraCont);
		CompraServicio compraServ = new CompraServicio(compraCont);
		
		//SERVICIES LOCATOR
		TiendaServiceLocator locator = new TiendaServiceLocator();
		locator.registrar("articulos", artServ);
		locator.registrar("usuarios", userServ);
		locator.registrar("carrito",carritoServ);
		locator.registrar("compras", compraServ);
		
		//EJECUCION
		try(Scanner sc = new Scanner(System.in)){
			System.out.println("=== SISTEMA TIENDA ===");
            System.out.println("Ejemplos de comandos:");
            System.out.println("  articulos/crear?codigo=A1&descripcion=Lapiz&precio=100&stock=50");
            System.out.println("  usuarios/crear?username=ana&password=1234&saldo=5000");
            System.out.println("  carrito/crear?username=ana");
            System.out.println("  carrito/agregar?username=ana&codigo=A1&cantidad=2");
            System.out.println("  carrito/ver?username=ana");
            System.out.println("  carrito/finalizar?username=ana");
            System.out.println("  compras/listar?usuario=ana");
            System.out.println("Salir: exit");
            System.out.println("--------------------------------------");
            
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
                Object servicio = locator.get(soli.getRecurso());
                if (servicio == null) {
                    System.out.println("ERROR: no existe servicio para: " + soli.getRecurso());
                    continue;
                }
                
                try {
                    Object resultado = null;

                    if (servicio instanceof ArticuloServicio s) resultado = s.ejecutar(soli);
                    else if (servicio instanceof UsuarioServicio s) resultado = s.ejecutar(soli);
                    else if (servicio instanceof CarritoServicio s) resultado = s.ejecutar(soli);
                    else if (servicio instanceof CompraServicio s) resultado = s.ejecutar(soli);

                    if (resultado instanceof List<?> lista)
                        lista.forEach(System.out::println);
                    else
                        System.out.println(resultado);

                } catch (Exception e) {
                    System.out.println("ERROR en ejecución: " + e.getMessage());
                }
            }
		}
        
	}

}
