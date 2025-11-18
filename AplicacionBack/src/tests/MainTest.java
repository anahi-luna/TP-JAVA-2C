package tests;

import java.util.Scanner;

import container.ArticuloContenedor;
import exceptions.ValidadorException;
import models.Articulo;
import validation.ArticuloValidador;
import validation.ValidadorNumero;
import validation.ValidadorString;

public class MainTest {

	public static void main(String[] args) {
		/*  //TEST CONTENEDOR
		ArticuloContenedor cont = new ArticuloContenedor();	
	
		Articulo a1 = new Articulo("A1","Lapicera",2000.0,5);
		Articulo a2 = new Articulo("B1","Cuaderno",3000.0,4);
	
		cont.agregar(a1);
		cont.agregar(a2);
		
		System.out.println("Listado: ");
		cont.getTodos().forEach(System.out::println);
		
		Articulo buscado = cont.buscar("A1");
		System.out.println("Buscado: "+buscado)*/
		
		
		/*//TEST VALIDADOR
		
		//ARTICULO-VALIDADOR
		try {
			Articulo a = new Articulo("A1", "Lapicera",2, -3);
			ArticuloValidador.validar(a);
			System.out.println("Validado ok!");
		}catch (ValidadorException e) {
			System.out.println("Error: "+ e.getMessage());
		}*/
		
	
	
	
	}

}
