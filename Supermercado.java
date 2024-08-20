/* TAREA:
Imagina que estás creando un juego de supermercado en tu computadora. Necesitamos que escribas un programa en Java para que todo funcione como en la vida real.
¿Cómo lo haremos?
- Humane: Tenemos una clase base para representar a cualquier persona (empleados y clientes).
- Empleados: Son humanes que trabajan en las cajas y tienen un sueldo.
- Cajas: Son los lugares donde se pagan las compras y están atendidas por un empleado o empleada.
- Clientes: También son humanes, pero además sabemos si son mayoristas o no (para darles descuentos).
Tu misión:
- Crea los productos: Cada producto tiene un nombre, precio y cantidad.
- Registra las compras: Cuando un cliente compra, se crea una transacción. Esta transacción guarda qué productos compró, cuánto pagó y en qué caja lo hizo.
- Simula una compra: Haz que un cliente vaya a una caja, compre varios productos y pague. ¡Muestra en pantalla todo lo que compró y cuánto pagó! */

class Humane {
    private String nombre;
    private String apellido;
    private int dni;
  
    public Humane(String nombre, String apellido, int dni) {
      this.nombre = nombre;
      this.apellido = apellido;
      this.dni = dni;
    }
  
    public String toString() {
        return "\n- Nombre: " + this.nombre + "\n- Apellido: " + this.apellido + "\n- DNI: " + this.dni;
    }
}

class Empleado extends Humane {
    private int sueldo;
  
    public Empleado(String nombre, String apellido, int dni, int sueldo) {
      super(nombre, apellido, dni);
      this.sueldo = sueldo;
    }
  
    public String toString() {
      return super.toString() + "\n- Sueldo: " + this.sueldo;
    }
}

class Caja {
    private Empleado empleado;
    private int nroCaja;
  
    public Caja(Empleado empleado, int nroCaja) {
      this.empleado = empleado;
      this.nroCaja = nroCaja;
    }
  
    public String toString() {
      return this.empleado.toString() + "\n- N° de caja: " + this.nroCaja;
    }
}

class Cliente extends Humane {
    private boolean esMayorista;
    private String tipoCliente;

    public Cliente(String nombre, String apellido, int dni, boolean esMayorista) {
        super(nombre, apellido, dni);
        this.esMayorista = esMayorista;
    }

    public boolean esMayorista() {
        return esMayorista;
    }

    public String getTipoCliente() {
        if (esMayorista()) {
            tipoCliente = "Mayorista";
        } else {
            tipoCliente = "Minorista";
        }
        return tipoCliente;
    }

    public String toString() {
        return super.toString() + "\n- Tipo: " + getTipoCliente();
    }
}

class Producto {
    private String nombre;
    private double precio;
    private int cantidad;
  
    public Producto(String nombre, double precio, int cantidad) {
      this.nombre = nombre;
      this.precio = precio;
      this.cantidad = cantidad;
    }
  
    public String getNombre() {
      return nombre;
    }
  
    public double getPrecio() {
      return precio;
    }
  
    public int getCantidad() {
      return cantidad;
    }
  
    public String toString() {
      return "- " + nombre + " (Precio: $" + precio + " - Cantidad: " + cantidad + ")";
    }
}
  
class Transaccion {
    private Cliente cliente;
    private Caja caja;
    private Producto[] productos;
    private double total;
  
    public void calcularTotal() {
      total = 0;
      for (Producto producto : productos) {
        total += producto.getPrecio() * producto.getCantidad();
      }
  
      if (cliente.esMayorista()) {
        total *= 0.9; 
      }
    }

    public Transaccion(Cliente cliente, Caja caja, Producto[] productos) {
      this.cliente = cliente;
      this.caja = caja;
      this.productos = productos;
      calcularTotal();
    }
  
    public String toString() {
      String detalle = "CLIENTE: " + cliente.toString() + "\n" + "\nEMPLEADO/A: " + caja.toString() + "\n" + "\nPRODUCTOS COMPRADOS: \n";

      for (Producto producto : productos) {
        detalle += producto.toString() + "\n";
      }
  
      detalle += "\nTOTAL A PAGAR: $" + total;
      return detalle;
    }
}
  
class Supermercado {
    public static void main(String[] args) {
      Producto p1 = new Producto("Harina", 1000, 10);
      Producto p2 = new Producto("Gaseosa", 2500, 15);
  
      Empleado empleado = new Empleado("Cecilia", "Torres", 30456321, 500000);
      Caja caja = new Caja(empleado, 3);
  
      Cliente cliente = new Cliente("Juan Pablo", "Kass", 43886600, true);
  
      Producto[] productosComprados = { p1, p2 };
      Transaccion transaccion = new Transaccion(cliente, caja, productosComprados);

      System.out.println(transaccion);
    }
}