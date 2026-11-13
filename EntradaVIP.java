/**
 * Subclase que representa una Entrada VIP de cine.
 * Precio por unidad: 30000 pesos.
 * Descuento: 10 por ciento sobre el subtotal de la compra.
 * Beneficios adicionales: Acceso a sala VIP y sillas reclinables.
 */
public class EntradaVIP extends Entrada {

    public static final double PRECIO_VIP = 30000.0;
    public static final double PORCENTAJE_DESCUENTO = 0.10;

    public EntradaVIP(String nombreCliente, int cantidad) throws CompraInvalidaException {
        super(nombreCliente, cantidad, PRECIO_VIP);
    }

    @Override
    public double calcularDescuento() {
        return calcularSubtotal() * PORCENTAJE_DESCUENTO;
    }

    @Override
    public int obtenerPorcentajeDescuento() {
        return 10;
    }

    @Override
    public String obtenerTipoEntrada() {
        return "Entrada VIP";
    }

    @Override
    public String obtenerBeneficios() {
        return "Acceso a sala VIP y sillas reclinables";
    }
}
