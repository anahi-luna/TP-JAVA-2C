package models;

public class Empleado extends Usuario {
    public Empleado(String nombreUsuario, String contrasena) {
        super(nombreUsuario, contrasena);
    }
    @Override
    public String getTipo() { return "Empleado"; }
}