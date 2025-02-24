package Classes.Instruction;

import Classes.Abstract.Expresion;
import Classes.Abstract.Instruccion;
import Classes.Around.Entorno;
import Classes.Utility.TipoInstruccion;

public class Regla extends Instruccion {
    Expresion condicion;
    Expresion acion;
    public Regla(Expresion condicion, Expresion acion) {
        super(TipoInstruccion.REGLA);
        this.condicion = condicion;
        this.acion = acion;
    }

    public void jugar(Entorno entorno) {
        if (condicion != null) {
            condicion.jugar(entorno);
        }
        acion.jugar(entorno);
    }
}