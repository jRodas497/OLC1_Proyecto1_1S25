package Classes.Abstract;

import Classes.Around.Entorno;
import Classes.Utility.TipoExpresion;
import Classes.Utility.TipoRetorno;
import Classes.Utility.TipoSentencia;

public abstract class Expresion extends Sentencia{
    TipoExpresion tipoExpresion;
    public Expresion(TipoExpresion tipoExpresion) {
        super(TipoSentencia.EXPRESION);
        this.tipoExpresion = tipoExpresion;
    }

    public abstract TipoRetorno jugar(Entorno entorno);
}
