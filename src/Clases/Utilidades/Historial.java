package Clases.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class Historial {
    private List<Boolean> historialJugador1 = new ArrayList<>();
    private List<Boolean> historialJugador2 = new ArrayList<>();
    private boolean estadoJugadorActual;

    public Historial(boolean estadoInicial) {
        this.estadoJugadorActual = estadoInicial;
    }

    public void cambiarEstadoJugador() {
        this.estadoJugadorActual = !this.estadoJugadorActual;
    }

    // true = cooperación, false = defección
    public void agregarResultado(boolean resultadoJugador1, boolean resultadoJugador2) {
        historialJugador1.add(resultadoJugador1);
        historialJugador2.add(resultadoJugador2);

        if (resultadoJugador1 && resultadoJugador2) {
            // ambos cooperan
            Salida.historialJugador1.add("C");
            Salida.historialJugador2.add("C");
        } else if (!resultadoJugador1 && !resultadoJugador2) {
            // ambos traicionan
            Salida.historialJugador1.add("D");
            Salida.historialJugador2.add("D");
        } else if (resultadoJugador1 && !resultadoJugador2) {
            // jugador 1 coopera, jugador 2 traiciona
            Salida.historialJugador1.add("C");
            Salida.historialJugador2.add("D");
        } else {
            // jugador 1 traiciona, jugador 2 coopera
            Salida.historialJugador1.add("D");
            Salida.historialJugador2.add("C");
        }
    }

    public List<Boolean> obtenerHistorial(boolean esPropio) {
        return esPropio ? historialJugador1 : historialJugador2;
    }

    public void imprimirHistorial(List<Boolean> historial) {
        for (int i = 0; i < historial.size(); i++) {
            System.out.println("Ronda " + i + ": " + (historial.get(i) ? "C" : "D"));
        }
    }
}