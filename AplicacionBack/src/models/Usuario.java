package models;

public abstract class Usuario {
    protected String nombreUsuario;
    protected String contrasena;
    protected double saldo; // Módulo de Saldo

    public Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.saldo = 0.0;
    }

    public String getNombreUsuario() { return nombreUsuario; }
    public String getContrasena() { return contrasena; }
    public double getSaldo() { return saldo; }
    
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public abstract String getTipo();
}