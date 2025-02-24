package Classes.Around;

import java.util.Map;
import java.util.TreeMap;

import Classes.Objects.Estrategia;
import Classes.Objects.Partida;

public class Entorno {
    String nombre;
    public Map<String, Estrategia> estrategias = new TreeMap<>();
    public Map<String, Partida> partidas = new TreeMap<>();

    public Entorno(String nombre) {
        this.nombre = nombre;
    }

    public boolean guardarEstrategia(String nombre, Estrategia estrategia) {
        if (!this.estrategias.containsKey(nombre)) {
            // System.out.println("ESTR: " + nombre);
            this.estrategias.put(nombre, estrategia);
            return true;
        }
        return false;
    }

    public boolean guardarPartida(String nombre, Partida partida) {
        if (!this.partidas.containsKey(nombre)) {
            this.partidas.put(nombre, partida);
            // System.out.println("Se está guardando la partida");
            return true;
        }
        return false;
    }

    public Partida obtenerPartida(String nombre){
        if (partidas.containsKey(nombre)) {
            // System.out.println("Encuentra la partida");
            return partidas.get(nombre);
        }
        return null;
    }

    public Estrategia obtenerEstrategia(String nombre){
        if (estrategias.containsKey(nombre)) {
            // System.out.println("Encuentra la estrategia");
            return estrategias.get(nombre);
        }
        return null;
    }
}