package Classes.Instruction;

import Classes.Abstract.Expresion;
import Classes.Abstract.Instruccion;
import Classes.Around.Entorno;
import Classes.Objects.Jugadores;
import Classes.Objects.Puntos;
import Classes.Utility.TipoInstruccion;

public class InstruccionesPartida extends Instruccion{
    Jugadores jugadores;
    Expresion rondas;
    Puntos puntos;
    public InstruccionesPartida(Jugadores jugadores, Expresion rondas, Puntos puntos) {
        super(TipoInstruccion.PARTIDA);
        this.jugadores = jugadores;
        this.rondas = rondas;
        this.puntos = puntos;
    }

    public void jugar(Entorno entorno) {
        rondas.jugar(entorno);
        puntos.cooperacion.jugar(entorno);
        puntos.defeccion.jugar(entorno);
        puntos.traidor.jugar(entorno);
        puntos.traicionado.jugar(entorno);
    }
}