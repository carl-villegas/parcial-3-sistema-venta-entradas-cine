/**
 * Clase abstracta que representa una entrada de cine generalizando los atributos
 * y comportamientos comunes. Implementa multiples interfaces (Calculable, Imprimible).
 * Cumple con los requerimientos A (Herencia y Generalizacion),
 * B (Polimorfismo y Clases Abstractas) y C (Implementacion de multiples interfaces).
 */
public abstract class Entrada implements Calculable, Imprimible {

    private String nombreCliente;
    private int cantidad;
    private double precioUnitario;

    public Entrada(String nombreCliente, int cantidad, double precioUnitario) throws CompraInvalidaException {
        validarDatos(nombreCliente, cantidad);
        this.nombreCliente = nombreCliente.trim();
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    /**
     * Valida las reglas del negocio utilizando throw y throws.
     */
    private void validarDatos(String nombreCliente, int cantidad) throws CompraInvalidaException {
        if (nombreCliente == null || nombreCliente.trim().isEmpty()) {
            throw new CompraInvalidaException("El nombre del cliente no puede estar vacio.");
        }
        if (cantidad <= 0) {
            throw new CompraInvalidaException("La cantidad de entradas debe ser mayor que cero.");
        }
        if (cantidad > 10) {
            throw new CompraInvalidaException("No se pueden comprar mas de 10 entradas en una sola operacion.");
        }
    }

    @Override
    public double calcularSubtotal() {
        return cantidad * precioUnitario;
    }

    @Override
    public abstract double calcularDescuento();

    public abstract int obtenerPorcentajeDescuento();

    @Override
    public abstract String obtenerTipoEntrada();

    @Override
    public abstract String obtenerBeneficios();

    @Override
    public double calcularTotal() {
        return calcularSubtotal() - calcularDescuento();
    }

    @Override
    public String generarResumen() {
        StringBuilder sb = new StringBuilder();
        sb.append("RESUMEN DE COMPRA\n\n");
        sb.append("Cliente: ").append(nombreCliente).append("\n");
        sb.append("Tipo: ").append(obtenerTipoEntrada()).append("\n");
        sb.append("Cantidad: ").append(cantidad).append("\n");
        sb.append("Precio unitario: ").append((long) precioUnitario).append(" pesos\n");
        sb.append("Subtotal: ").append((long) calcularSubtotal()).append(" pesos\n");
        sb.append("Descuento (").append(obtenerPorcentajeDescuento()).append(" por ciento): ")
          .append((long) calcularDescuento()).append(" pesos\n");
        sb.append("Beneficios adicionales: ").append(obtenerBeneficios()).append("\n");
        sb.append("Total a pagar: ").append((long) calcularTotal()).append(" pesos");
        return sb.toString();
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }
}
