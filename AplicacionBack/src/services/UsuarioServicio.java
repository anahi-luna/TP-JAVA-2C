package services;

import java.util.List;
import java.util.Map;

import container.UsuarioContenedor;
import enums.UsuarioTipo;
import exceptions.ValidadorException;
import parsers.Solicitud;
import validation.UsuarioValidador;
import interfaces.Servicio;
import models.Usuario;

public class UsuarioServicio implements Servicio{
    private final UsuarioContenedor contenedor;

    public UsuarioServicio(UsuarioContenedor contenedor) {
        this.contenedor = contenedor;
    }

	@Override
	public Object ejecutar(Solicitud solicitud) {
		String accion = solicitud.getAccion() == null? "": solicitud.getAccion().toLowerCase();
		Map<String, String> p = solicitud.getParametros();
		
		try {
			switch (accion) {
			case "crear":
			case "alta":
				Usuario u = crearUsuarioDesdeParams(p);
				UsuarioValidador.validarUsuarioParaAlta(u);
				if (contenedor.existe(u.getNombreUsuario())) {
                    return "ERROR: usuario ya existe";
                }
                contenedor.crear(u);
                return "OK: usuario creado: " + u.getNombreUsuario();
			
			case "login":
			case "validar":
				String user = p.get("username");
				String pass = p.get("password");
				UsuarioValidador.validarCredenciales(user, pass);
				Usuario encontrado = contenedor.buscar(user);
				if (encontrado == null) return "ERROR: usuario no encontrado";
				if (!encontrado.getContrasena().equals(pass)) return "ERROR: credenciales incorrectas";
				return encontrado;
			
			case "listar":
				List<Usuario> todos = contenedor.getTodos();
				return todos;
			
			case "buscar":
				String username = p.get("username");
                Usuario buscado = contenedor.buscar(username);
                return buscado == null ? ("ERROR: no encontrado " + username) : buscado;
             
			case "agregarsaldo":  
				String userTo = p.get("username");
                String montoStr = p.get("monto");
                Usuario uTarget = contenedor.buscar(userTo);
                if (uTarget == null) return "ERROR: usuario no encontrado";
                try {
                    double monto = Double.parseDouble(montoStr);
                    if (monto <= 0) return "ERROR: monto debe ser > 0";
                    uTarget.setSaldo(uTarget.getSaldo() + monto);
                    return "OK: saldo actualizado. Nuevo saldo: " + uTarget.getSaldo();
                } catch (NumberFormatException nfe) {
                    return "ERROR: monto inválido";
                }
			case "transferir":
				String origen = p.get("origen");
                String destino = p.get("destino");
                String montoS = p.get("monto");
                Usuario uOrigen = contenedor.buscar(origen);
                Usuario uDestino = contenedor.buscar(destino);
                if (uOrigen == null) return "ERROR: origen no encontrado";
                if (uDestino == null) return "ERROR: destino no encontrado";
                double monto;
                try {
                    monto = Double.parseDouble(montoS);
                } catch (NumberFormatException ex) {
                    return "ERROR: monto inválido";
                }
                if (monto <= 0) return "ERROR: monto debe ser > 0";
                if (uOrigen.getSaldo() < monto) return "ERROR: saldo insuficiente";
                uOrigen.setSaldo(uOrigen.getSaldo() - monto);
                uDestino.setSaldo(uDestino.getSaldo() + monto);
                return "OK: transferencia realizada";
                
			case "eliminar":
				String toDelete = p.get("username");
                boolean del = contenedor.eliminar(toDelete);
                return del ? "OK: usuario eliminado" : "ERROR: usuario no encontrado";
                
			default:
				return "ERROR: acción desconocida: " + accion;
			}
		} catch (ValidadorException ve) {
            return "ERROR_VALIDACION: " + ve.getMessage();
        } catch (RuntimeException re) {
            return "ERROR_RUNTIME: " + re.getMessage();
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
	}
	
	public Usuario crearUsuarioDesdeParams(Map<String,String> p) {
		String username = p.get("username");
		String password = p.getOrDefault("password", "");
		String tipoStr = p.getOrDefault("tipo", "CLIENTE").toUpperCase();
		UsuarioTipo tipo;
		
		try {
			tipo = UsuarioTipo.valueOf(tipoStr);
		} catch (Exception e) {
			tipo = UsuarioTipo.CLIENTE;
		}
		double saldo = 0.0;
		if(p.get("saldo") != null) {
			try {
				saldo = Double.parseDouble(p.get("saldo"));
			}catch (NumberFormatException ignored) {}
		}
		Usuario u = new Usuario(username,password,tipo);
		u.setSaldo(saldo);
		return u;
		
	}
    
    

   
   
     /* //Crea y agrega un nuevo usuario si es v�lido y no existe.
     
    public Usuario crearUsuario(String nombreUsuario, String contrasena, UsuarioTipo tipo) {
        if (!UsuarioValidator.esNombreUsuarioValido(nombreUsuario) || 
            !UsuarioValidator.esContrasenaValida(contrasena)) 
        {
            
            System.err.println("Error: Nombre de usuario o contrase�a no v�lidos.");
            return null;
        }

        if (contenedor.getUsuario(nombreUsuario) != null) {
            System.err.println("Error: El nombre de usuario ya existe.");
            return null;
        }

        Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasena, tipo);
        contenedor.agregarUsuario(nuevoUsuario);
        return nuevoUsuario;
    }

 
    
      //Valida si las credenciales coinciden con un usuario existente.
    
    public Usuario validarCredenciales(String nombreUsuario, String contrasena) {
        Usuario usuario = contenedor.getUsuario(nombreUsuario);

        if (usuario == null) {
            return null;
        }

        
        if (usuario.getContrasena().equals(contrasena)) {
            return usuario;
        } else {
           
            return null;
        }
    }

   
    
     //Agrega dinero al saldo de un usuario.
   
  
    public boolean agregarSaldo(String nombreUsuario, double monto) {
        if (!UsuarioValidator.esMontoPositivo(monto)) {
            return false;
        }

        Usuario usuario = contenedor.getUsuario(nombreUsuario);
        if (usuario != null) {
            usuario.setSaldo(usuario.getSaldo() + monto);
            return true;
        }
        return false;
    }

 
     //Transfiere dinero de un usuario a otro.
   
    public int transferirSaldo(String origenNombre, String destinoNombre, double monto) {
        if (!UsuarioValidator.esMontoPositivo(monto)) {
            return 4;
        }

        Usuario origen = contenedor.getUsuario(origenNombre);
        Usuario destino = contenedor.getUsuario(destinoNombre);

        if (origen == null) {
            return 1; // Origen no encontrado
        }
        if (destino == null) {
            return 2; // Destino no encontrado
        }
        if (origen.getSaldo() < monto) {
            return 3; // Saldo insuficiente
        }

        // Realizar la transferencia
        origen.setSaldo(origen.getSaldo() - monto);
        destino.setSaldo(destino.getSaldo() + monto);

        return 0; // �xito
    }

    public Map<String, Usuario> listarTodos() {
        return contenedor.listarTodos();
    }*/
}