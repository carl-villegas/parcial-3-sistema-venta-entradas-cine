/**
 * Subclase que representa una Entrada General de cine.
 * Precio por unidad: 15000 pesos.
 * Descuento: 0 por ciento.
 * Beneficios adicionales: No aplica.
 */
public class EntradaGeneral extends Entrada {

    public static final double PRECIO_GENERAL = 15000.0;

    public EntradaGeneral(String nombreCliente, int cantidad) throws CompraInvalidaException {
        super(nombreCliente, cantidad, PRECIO_GENERAL);
    }

    @Override
    public double calcularDescuento() {
        return 0.0;
    }

    @Override
    public int obtenerPorcentajeDescuento() {
        return 0;
    }

    @Override
    public String obtenerTipoEntrada() {
        return "Entrada general";
    }

    @Override
    public String obtenerBeneficios() {
        return "No aplica";
    }
}
