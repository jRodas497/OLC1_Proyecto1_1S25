import Vistas.MainView;

public class Main {
    public static void main(String[] args) {
        try {
            // Ejecutar MainView
            MainView.main(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}