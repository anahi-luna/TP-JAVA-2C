package comunicacion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import interfaces.Comunicador;

public class ComunicadorSocketCliente implements Comunicador{
	private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    
    public ComunicadorSocketCliente(Socket socket) throws Exception {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

	@Override
	public void enviar(String mensaje) {
		out.println(mensaje);
		
	}

	@Override
	public String recibir() {
		try {
            return in.readLine();
        } catch (Exception e) {
            return null;
        }
	}
	
	public void cerrar() {
        try {
            socket.close();
        } catch (Exception ignored) {}
    }
    
    
}
