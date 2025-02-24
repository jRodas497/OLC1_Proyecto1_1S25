package Classes.Abstract;

import Classes.Around.Entorno;
import Classes.Utility.TipoInstruccion;
import Classes.Utility.TipoSentencia;

public abstract class Instruccion extends Sentencia{
    TipoInstruccion tipoInstruccion;
    public Instruccion(TipoInstruccion tipoInstruccion) {
        super(TipoSentencia.INSTRUCION);
        this.tipoInstruccion = tipoInstruccion;
    }

    public abstract void jugar(Entorno entorno);
}