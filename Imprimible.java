/**
 * Interfaz que define el contrato para la presentacion de informacion y resumen
 * de una entrada de cine.
 * Cumple con el requerimiento C (Interfaces).
 */
public interface Imprimible {

    String obtenerTipoEntrada();

    String obtenerBeneficios();

    String generarResumen();
}
