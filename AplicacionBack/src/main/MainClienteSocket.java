package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import comunicacion.ComunicadorSocketCliente;

public class MainClienteSocket {
	
	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 5000);
	             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	             Scanner scanner = new Scanner(System.in)) {

	            System.out.println("Conectando al servidor...");
	            System.out.println("📡 Conectado!");

	            // HILO PARA RECIBIR MENSAJES
	            Thread receptor = new Thread(() -> {
	                try {
	                    while (true) {
	                        String linea = in.readLine();
	                        if (linea == null) break;

	                        if (!linea.isEmpty()) {
	                            System.out.println("servidor> " + linea);
	                        } else {
	                            System.out.println();
	                            
	                            System.out.print("cliente> ");
	                        }
	                    }
	                } catch (IOException e) {
	                    System.out.println("⚠ Error al recibir datos del servidor");
	                }
	            });
	            receptor.start();

	            // HILO PARA ENVIAR
	            while (true) {
	                String msg = scanner.nextLine();
	                out.println(msg);

	                if (msg.equalsIgnoreCase("salir")) break;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        System.out.println("Cliente desconectado.");
	}
	
}
