# Sistema de Tienda (Cliente/Servidor con Sockets + Hilos)
**👥 Integrantes**
- Luz Gonzalez
- Daniel Espindola
- Rebeca Anahí Luna Colque
  
🚀 **Cómo ejecutar el sistema**

# 1️ Iniciar el Servidor

**Ejecutar:**
```bash
src/main/MainServer.java
```
**Salida esperada:**
```bash
>>> Usuario admin precargado (admin / 1234)
Servidor iniciando en puerto 5000...
Servidor escuchando...
```
➡ Esto confirma que el usuario admin ya está disponible.

# 2️⃣ Iniciar el Cliente

**Ejecutar:**
```bash
src/main/MainClienteSocket.java
```
**Salida esperada:**
```bash
Conectando al servidor...
📡 Conectado!
servidor> === Bienvenido a la Tienda ===
servidor> Debe iniciar sesión. Formato:
servidor> login username=xxx password=xxx
cliente>
```
Ya podés comenzar a enviar comandos.

# 🔐 Iniciar sesión
```bash
usuario/login?username=admin&password=1234
```

# 🧪 COMANDOS DE PRUEBA
**📦 ARTÍCULOS**
```bash
articulo/crear?codigo=A2&descripcion=Cuaderno+Rayado&precio=450&stock=50
articulo/crear?codigo=A3&descripcion=Goma+Borra&precio=80&stock=30
articulo/listar
articulo/buscar?codigo=A1
articulo/editar?codigo=A1&descripcion=Lapiz+Negro&precio=150
articulo/eliminar?codigo=A3
```

**👤 USUARIOS**

(admin ya está creado)
```bash
usuario/crear?username=clienteA&password=passA&tipo=CLIENTE
usuario/listar
usuario/login?username=admin&password=1234
usuario/agregarsaldo?username=clienteA&monto=200
usuario/transferir?origen=clienteA&destino=admin&monto=50
usuario/buscar?username=clienteA
usuario/eliminar?username=clienteA
usuario/listar
usuario/versaldo
logout  //Cerrar sesion
```

**🛒 CARRITO + COMPRAS**
```bash
carrito/agregar?codigo=A2&cantidad=2
carrito/ver
carrito/finalizar
carrito/listarcompras
compra/listar
```

**Salir del programa**
```bash
salir
```
