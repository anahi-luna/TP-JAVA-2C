package models;
import enumerable.TipoUsuario;

public class Usuario {
	private String id;
	private String nombreUsuario;
	private double saldo; 
	private String contrasenia;
	private TipoUsuario tipo; 

	public Usuario(String id, String nombreUsuario, double saldo) {
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.saldo = saldo;
		this.contrasenia = contrasenia;
		this.tipo = tipo; 
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	
	
}
