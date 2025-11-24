package models;

import enums.UsuarioTipo;

public class Usuario {
    private String nombreUsuario;
    private String contrasena;
    private UsuarioTipo tipo;
    private double saldo;
    
    public Usuario() {}

    // Constructor
    public Usuario(String nombreUsuario, String contrasena, UsuarioTipo tipo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.saldo = 0.0; // Inicializamos el saldo en cero
    }

    // Getters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public UsuarioTipo getTipo() {
        return tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setTipo(UsuarioTipo tipo) {
		this.tipo = tipo;
	}

	@Override
    public String toString() {
        return "Usuario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", tipo=" + tipo +
                ", saldo=" + saldo +
                '}';
    }
}