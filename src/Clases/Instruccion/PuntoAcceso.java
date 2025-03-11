package Clases.Instruccion;

import Clases.Abstractas.Expresion;
import Clases.Abstractas.Instruccion;
import Clases.Entorno.Entorno;
import Clases.Objetos.Estrategia;
import Clases.Objetos.Partida;
import Clases.Utilidades.Salida;
import Clases.Utilidades.TipoInstruccion;

public class PuntoAcceso extends Instruccion {
    Object partida;
    int rondas;
    boolean decision1;
    boolean decision2;
    int cooperacion = 0;
    int defeccion = 0;
    int traidor = 0;
    int traicionado = 0;
    int puntuacion1 = 0;
    int puntuacion2 = 0;
    int cooperaciones1 = 0;
    int defecciones1 = 0;
    int cooperaciones2 = 0;
    int defecciones2 = 0;

    Estrategia estrategia1;
    Estrategia estrategia2;

    public PuntoAcceso(Object partida) {
        super(TipoInstruccion.MAIN);
        this.partida = partida;
    }

    public void jugar(Entorno entorno) {
        Partida partida = entorno.obtenerPartida(this.partida.toString());
        if (partida != null) {
            this.rondas = (int) partida.rondas;
            entorno.setPartidaActual(partida);

            estrategia1 = entorno.obtenerEstrategia(partida.jugador1);
            estrategia2 = entorno.obtenerEstrategia(partida.jugador2);

            cooperacion = (int) partida.puntos.cooperacion.jugar(entorno).valor;
            defeccion = (int) partida.puntos.defeccion.jugar(entorno).valor;
            traidor = (int) partida.puntos.traidor.jugar(entorno).valor;
            traicionado = (int) partida.puntos.traicionado.jugar(entorno).valor;

            Salida.salidaConsola.add("========================================================================");
            Salida.salidaConsola.add("                                                                            PARTIDA");
            Salida.salidaConsola.add("========================================================================");
            Salida.salidaConsola.add("\n  \uD83D\uDCCC CONFIGURACIÓN");
            Salida.salidaConsola.add("\t  \uD83C\uDFAE Estrategias: " + estrategia1.nombre + " vs " + estrategia2.nombre);
            Salida.salidaConsola.add("\t  \uD83D\uDD04 Rondas: " + rondas);
            Salida.salidaConsola.add("\t  \uD83C\uDFC6 Puntuación: ");
            Salida.salidaConsola.add("\t        \uD83E\uDD1D Cooperación Mutua: " + cooperacion);
            Salida.salidaConsola.add("\t        \uD83D\uDCAC Declaración Mutua: " + defeccion);
            Salida.salidaConsola.add("\t        ⚡ Traición: " + traidor + "/" + traicionado + " (Traidor/Traicionado)");
            Salida.salidaConsola.add("\n [ DESARROLLO ]");
            Salida.salidaConsola.add("====================================");
            Salida.salidaConsola.add("\tRonda\t|\t" + estrategia1.nombre + "\t|\t" + estrategia2.nombre + "\t|\tPuntuación\n");

            for (int i = 0; i < rondas; i++) {
                entorno.setRondaActual(i);
                if (i == 0) {
                    decision1 = decisionInicial(entorno, estrategia1);
                    decision2 = decisionInicial(entorno, estrategia2);
                    formato(i, estrategia1.nombre, estrategia2.nombre);
                } else {
                    decision1 = (boolean) validarRegla(entorno, estrategia1);
                    decision2 = (boolean) validarRegla(entorno, estrategia2);
                    formato(i, estrategia1.nombre, estrategia2.nombre);
                }
            }
        }
        Salida.salidaConsola.add(" \n \uD83D\uDCCC RESULTADO");
        Salida.salidaConsola.add("========================================================================");
        Salida.salidaConsola.add("\t \uD83C\uDF96\uFE0F Puntuación " + estrategia1.nombre + ": " + puntuacion1 + " \n\t\t --> (" + getPercentage(defecciones1, rondas) + "% D, " + getPercentage(cooperaciones1, rondas) + "% C)");
        Salida.salidaConsola.add("\t \uD83C\uDF96\uFE0F Puntuación " + estrategia2.nombre + ": " + puntuacion2 + " \n\t\t --> (" + getPercentage(defecciones2, rondas) + "% D, " + getPercentage(cooperaciones2, rondas) + "% C)");
        Salida.salidaConsola.add("========================================================================");
    }

    public boolean decisionInicial(Entorno entorno, Estrategia estrategia) {
        Expresion decisionInicial = estrategia.instrucciones.inicio;
        return (boolean) decisionInicial.jugar(entorno).valor;
    }

    public Object validarRegla(Entorno entorno, Estrategia estrategia) {
        Object decisonDefault = null;

        for (Regla regla : estrategia.instrucciones.reglas) {
            if (regla.condicion != null && (boolean) regla.condicion.jugar(entorno).valor) {
                return regla.acion.jugar(entorno).valor;
            } else if (regla.condicion == null) {
                decisonDefault = regla.acion.jugar(entorno).valor;
            }
        }
        return decisonDefault;
    }

    public void formato(int i, String estrategia1, String estrategia2) {
        int ronda = i + 1;
        if (decision1 && decision2) { // Cooperacon mutua
            Salida.salidaConsola.add("\t" + ronda + "\t|" + "\tC\t" + "|\t" + "C\t|\t(" + cooperacion + "-" + cooperacion + ")");
            puntuacion1 += cooperacion;
            puntuacion2 += cooperacion;
            cooperaciones1++;
            cooperaciones2++;
        } else if (!decision1 && !decision2) { // Defección mutua
            Salida.salidaConsola.add("\t" + ronda + "\t|" + "\tD\t" + "|\t" + "D\t|\t(" + defeccion + "-" + defeccion + ")");
            puntuacion1 += defeccion;
            puntuacion2 += defeccion;
            defecciones1++;
            defecciones2++;
        } else if (!decision1 && decision2) { // Estrategia 1 defectuo - Estrategia coopero
            Salida.salidaConsola.add("\t" + ronda + "\t|" + "\tD\t" + "|\t" + "C\t|\t(" + traidor + "-" + traicionado + ")");
            puntuacion1 += traidor;
            puntuacion2 += traicionado;
            defecciones1++;
            cooperaciones2++;
        } else { // Estrategia 1 coopero - Estrartegia 2 defectua
            Salida.salidaConsola.add("\t" + ronda + "\t|" + "\tC\t" + "|\t" + "D\t|\t(" + traicionado + "-" + traidor + ")");
            puntuacion1 += traicionado;
            puntuacion2 += traidor;
            cooperaciones1++;
            defecciones2++;
        }
    }

    private String getPercentage(int count, int total) {
        return String.format("%.2f", (count * 100.0) / total);
    }
}