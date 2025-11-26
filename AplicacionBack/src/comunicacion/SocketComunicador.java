package comunicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import interfaces.Comunicador;

public class SocketComunicador implements Comunicador {

	private PrintWriter out;
	private BufferedReader in;
	
	public SocketComunicador(Socket socket) throws IOException{
		this.out = new PrintWriter(socket.getOutputStream(),true);
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	@Override
	public void enviar(String mensaje) {
		out.println(mensaje);
	}

	@Override
	public String recibir() {
		try {
			return in.readLine();
		}catch (IOException e) {
			return null;
		}
	}

}
