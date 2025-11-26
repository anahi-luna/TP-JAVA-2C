package comunicacion;

import java.util.Scanner;

import interfaces.Comunicador;

public class ConsoleComunicador implements Comunicador{

	private Scanner sc= new Scanner(System.in);
	
	@Override
	public void enviar(String mensaje) {
		System.out.println(mensaje);
	}

	@Override
	public String recibir() {
		System.out.print(">");
		return sc.nextLine();
	}
	
}
