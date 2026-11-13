import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaCine ventana = new VentanaCine();
                ventana.setVisible(true);
            }
        });
    }
}
