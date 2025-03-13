package Clases.Entorno;

import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;

import Clases.Objetos.Estrategia;
import Clases.Objetos.Partida;

public class Entorno {
    String nombre;
    public Map<String, Estrategia> estrategias = new TreeMap<>();
    public Map<String, Partida> partidas = new TreeMap<>();
    private Map<Boolean, List<Boolean>> historiales = new TreeMap<>();
    private boolean estadoJugadorActual;

    Partida partidaActual;
    int rondaActual;

    public Entorno(String nombre) {
        this.nombre = nombre;
        // al inicio el jugador "a" tiene el estado true
        this.estadoJugadorActual = true;
        historiales.put(true, new ArrayList<>());
        historiales.put(false, new ArrayList<>());
    }

    public boolean guardarEstrategia(String nombre, Estrategia estrategia) {
        if (!this.estrategias.containsKey(nombre)) {
            this.estrategias.put(nombre, estrategia);
            return true;
        }
        return false;
    }

    public boolean guardarPartida(String nombre, Partida partida) {
        if (!this.partidas.containsKey(nombre)) {
            this.partidas.put(nombre, partida);
            return true;
        }
        return false;
    }

    public Partida obtenerPartida(String nombre){
        if (partidas.containsKey(nombre)) {
            return partidas.get(nombre);
        }
        return null;
    }

    public Estrategia obtenerEstrategia(String nombre){
        if (estrategias.containsKey(nombre)) {
            return estrategias.get(nombre);
        }
        return null;
    }

    public void setPartidaActual(Partida partida) {
        this.partidaActual = partida;
    }

    public Partida getPartidaActual() {
        return this.partidaActual;
    }

    public void setRondaActual(int ronda) {
        this.rondaActual = ronda;
    }

    public int getRondaActual() {
        return this.rondaActual;
    }

    public void cambiarEstadoJugador() {
        this.estadoJugadorActual = !this.estadoJugadorActual;
    }

    public void agregarResultado(boolean resultado) {
        historiales.get(estadoJugadorActual).add(resultado);
    }

    public List<Boolean> obtenerHistorialPropio() {
        return historiales.get(estadoJugadorActual);
    }

    public List<Boolean> obtenerHistorialOponente() {
        return historiales.get(!estadoJugadorActual);
    }

    public void imprimirHistorial(List<Boolean> historial) {
        for (int i = 0; i < historial.size(); i++) {
            System.out.println("Ronda " + (i + 1) + ": " + (historial.get(i) ? "C" : "D"));
        }
    }
}