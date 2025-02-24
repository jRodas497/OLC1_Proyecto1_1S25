package Classes.Abstract;

import Classes.Utility.TipoSentencia;

public abstract class Sentencia {
    TipoSentencia tipoSentencia;
    public Sentencia(TipoSentencia tipoSentencia) {
        this.tipoSentencia = tipoSentencia;
    }
}