package Clases.Expresion;

import Clases.Abstractas.Expresion;
import Clases.Entorno.Entorno;
import Clases.Utilidades.Tipo;
import Clases.Utilidades.TipoExpresion;
import Clases.Utilidades.TipoRetorno;

public class Logica extends Expresion {
    Expresion exp1;
    Expresion exp2;
    String operador;

    public Logica(Expresion exp1, String operador, Expresion exp2) {
        super(TipoExpresion.LOGICA);
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operador = operador;
    }

    public Logica(String operador, Expresion exp1) {
        super(TipoExpresion.LOGICA);
        this.exp1 = exp1;
        this.exp2 = null;
        this.operador = operador;
    }

    public TipoRetorno jugar(Entorno entorno) {
        switch (this.operador) {
            case "&&":
                return and(entorno);
            case "||":
                return or(entorno);
            case "!":
                return not(entorno);
            default:
                return new TipoRetorno(-1, null);
        }
    }

    private TipoRetorno and(Entorno entorno) {
        TipoRetorno valor1 = exp1.jugar(entorno);
        TipoRetorno valor2 = exp2.jugar(entorno);
        if (valor1.tipo == Tipo.BOOLEAN && valor2.tipo == Tipo.BOOLEAN) {
            return new TipoRetorno((Boolean) valor1.valor && (Boolean) valor2.valor, Tipo.BOOLEAN);
        }
        return null;
    }

    private TipoRetorno or(Entorno entorno) {
        TipoRetorno valor1 = exp1.jugar(entorno);
        TipoRetorno valor2 = exp2.jugar(entorno);
        if (valor1.tipo == Tipo.BOOLEAN && valor2.tipo == Tipo.BOOLEAN) {
            return new TipoRetorno((Boolean) valor1.valor || (Boolean) valor2.valor, Tipo.BOOLEAN);
        }
        return null;
    }

    private TipoRetorno not(Entorno entorno) {
        TipoRetorno valor = exp1.jugar(entorno);
        if (valor.tipo == Tipo.BOOLEAN) {
            return new TipoRetorno(!(Boolean) valor.valor, Tipo.BOOLEAN);
        }
        return null;
    }
}