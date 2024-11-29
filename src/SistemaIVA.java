import java.util.ArrayList;
import java.util.Scanner;

class Producto {
    private String descripcion;
    private double valorBase; // Precio sin IVA

    public Producto(String descripcion, double valorBase) {
        this.descripcion = descripcion;
        this.valorBase = valorBase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public double getValorTotal() {
        return valorBase; // Sin IVA, redefinido en subclases
    }

    public double getImpuesto() {
        return 0; // Sin IVA, redefinido en subclases
    }

    public double getPorcentajeImpuesto() {
        return 0; // Sobrescrito en las subclases
    }

    public boolean aplicaImpuesto() {
        return getImpuesto() > 0;
    }
}

class IVA4 extends Producto {
    public static final double TASA = 4.0;

    public IVA4(String descripcion, double valorBase) {
        super(descripcion, valorBase);
    }

    @Override
    public double getImpuesto() {
        return (getValorBase() * TASA) / 100.0;
    }

    @Override
    public double getValorTotal() {
        return getValorBase() + getImpuesto();
    }

    @Override
    public double getPorcentajeImpuesto() {
        return TASA;
    }
}

class IVA7 extends Producto {
    public static final double TASA = 7.0;

    public IVA7(String descripcion, double valorBase) {
        super(descripcion, valorBase);
    }

    @Override
    public double getImpuesto() {
        return (getValorBase() * TASA) / 100.0;
    }

    @Override
    public double getValorTotal() {
        return getValorBase() + getImpuesto();
    }

    @Override
    public double getPorcentajeImpuesto() {
        return TASA;
    }
}

class IVA16 extends Producto {
    public static final double TASA = 16.0;

    public IVA16(String descripcion, double valorBase) {
        super(descripcion, valorBase);
    }

    @Override
    public double getImpuesto() {
        return (getValorBase() * TASA) / 100.0;
    }

    @Override
    public double getValorTotal() {
        return getValorBase() + getImpuesto();
    }

    @Override
    public double getPorcentajeImpuesto() {
        return TASA;
    }
}

public class SistemaIVA {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ArrayList<Producto> listaProductos = new ArrayList<>();
        System.out.println("¡Bienvenido al calculador de impuestos!");

        boolean continuar = true;
        while (continuar) {
            System.out.println("\nSeleccione el tipo de producto que desea agregar:");
            System.out.println("1. IVA4 (4% de impuesto)");
            System.out.println("2. IVA7 (7% de impuesto)");
            System.out.println("3. IVA16 (16% de impuesto)");
            System.out.println("4. Sin impuesto");
            int tipo = entrada.nextInt();
            entrada.nextLine(); // Consumir salto de línea

            System.out.print("Ingrese el nombre del producto: ");
            String descripcion = entrada.nextLine();

            System.out.print("Ingrese el precio del producto (sin impuesto): ");
            double valorBase = entrada.nextDouble();

            Producto producto = null;
            switch (tipo) {
                case 1:
                    producto = new IVA4(descripcion, valorBase);
                    break;
                case 2:
                    producto = new IVA7(descripcion, valorBase);
                    break;
                case 3:
                    producto = new IVA16(descripcion, valorBase);
                    break;
                case 4:
                    producto = new Producto(descripcion, valorBase);
                    break;
                default:
                    System.out.println("Tipo de producto no válido. Inténtelo nuevamente.");
                    continue;
            }

            listaProductos.add(producto);
            System.out.println("\nEl producto " + producto.getDescripcion() + " tiene un precio total con impuesto de $" +
                    producto.getValorTotal() + ", incluyendo un impuesto de $" + producto.getImpuesto() +
                    " (Porcentaje aplicado: " + producto.getPorcentajeImpuesto() + "%)");

            System.out.print("\n¿Desea agregar otro producto? (S/N): ");
            char opcion = entrada.next().toUpperCase().charAt(0);
            continuar = (opcion == 'S');
        }

        System.out.println("\nLista final de productos:");
        double totalConImpuesto = 0;
        for (Producto producto : listaProductos) {
            System.out.println("Producto: " + producto.getDescripcion() +
                    " | Precio base: $" + producto.getValorBase() +
                    " | Impuesto: $" + producto.getImpuesto() +
                    " | Precio total: $" + producto.getValorTotal() +
                    " | Porcentaje aplicado: " + producto.getPorcentajeImpuesto() + "%");
            totalConImpuesto += producto.getValorTotal();
        }
        System.out.println("\nTotal a pagar (con impuestos): $" + totalConImpuesto);

        entrada.close();
    }
}
