import Pruebas.ParserTest;
import Pruebas.ScannerTest;
import Vistas.MainView;

public class Main {
    public static void main(String[] args) {
        try {
            // Ejecutar ParserTest
            ParserTest.main(new String[0]);

            // Ejecutar ScannerTest
            ScannerTest.main(new String[0]);

            // Ejecutar MainView
            MainView.main(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}