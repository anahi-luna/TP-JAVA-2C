package models;

public class Cliente extends Usuario {
    public Cliente(String nombreUsuario, String contrasena) {
        super(nombreUsuario, contrasena);
    }
    @Override
    public String getTipo() { return "Cliente"; }
}