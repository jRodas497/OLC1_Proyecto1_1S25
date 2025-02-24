package Classes.Instruction;

import java.util.ArrayList;

import Classes.Abstract.Expresion;
import Classes.Abstract.Instruccion;
import Classes.Around.Entorno;
import Classes.Utility.TipoInstruccion;

public class InstruccionEstrategia extends Instruccion{
    Expresion inicio;
    public ArrayList<Regla> reglas;
    public InstruccionEstrategia (Expresion inicio, ArrayList<Regla> reglas) {
        super(TipoInstruccion.ESTRATEGIA);
        this.inicio = inicio;
        this.reglas = reglas;
    }

    public void jugar(Entorno entorno) {
        inicio.jugar(entorno);
        for (Regla regla : reglas) {
            regla.jugar(entorno);
        }
    }
}