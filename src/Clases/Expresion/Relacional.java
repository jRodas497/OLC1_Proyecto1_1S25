package Clases.Expresion;

import Clases.Abstractas.Expresion;
import Clases.Entorno.Entorno;
import Clases.Utilidades.Tipo;
import Clases.Utilidades.TipoExpresion;
import Clases.Utilidades.TipoRetorno;

public class Relacional extends Expresion {
    Expresion exp1;
    Expresion exp2;
    String operador;

    public Relacional(Expresion exp1, String operador, Expresion exp2) {
        super(TipoExpresion.RELACIONAL);
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operador = operador;
    }

    public TipoRetorno jugar(Entorno entorno) {
        switch (this.operador) {
            case "==":
                return igual(entorno);
            case "!=":
                return diferente(entorno);
            case ">":
                return mayor(entorno);
            case "<":
                return menor(entorno);
            case ">=":
                return mayorIgual(entorno);
            case "<=":
                return menorIgual(entorno);
            default:
                return new TipoRetorno(-1, null);
        }
    }

    public TipoRetorno igual(Entorno entorno) {
        TipoRetorno valor1 = exp1.jugar(entorno);
        TipoRetorno valor2 = exp2.jugar(entorno);
        if (valor1.tipo == Tipo.ENTERO || valor1.tipo == Tipo.DECIMAL) {
            if (valor2.tipo == Tipo.ENTERO || valor2.tipo == Tipo.DECIMAL) {
                return new TipoRetorno(Double.parseDouble(valor1.valor.toString()) == Double.parseDouble(valor2.valor.toString()), Tipo.BOOLEAN);
            }
        }
        return null;
    }

    private TipoRetorno diferente(Entorno entorno) {
        TipoRetorno valor1 = exp1.jugar(entorno);
        TipoRetorno valor2 = exp2.jugar(entorno);
        if (valor1.tipo == Tipo.ENTERO || valor1.tipo == Tipo.DECIMAL) {
            if (valor2.tipo == Tipo.ENTERO || valor2.tipo == Tipo.DECIMAL) {
                return new TipoRetorno(Double.parseDouble(valor1.valor.toString()) != Double.parseDouble(valor2.valor.toString()), Tipo.BOOLEAN);
            }
        }
        return null;
    }

    private TipoRetorno mayor(Entorno entorno) {
        TipoRetorno valor1 = exp1.jugar(entorno);
        TipoRetorno valor2 = exp2.jugar(entorno);
        if (valor1.tipo == Tipo.ENTERO || valor1.tipo == Tipo.DECIMAL) {
            if (valor2.tipo == Tipo.ENTERO || valor2.tipo == Tipo.DECIMAL) {
                return new TipoRetorno(Double.parseDouble(valor1.valor.toString()) > Double.parseDouble(valor2.valor.toString()), Tipo.BOOLEAN);
            }
        }
        return null;
    }

    private TipoRetorno menor(Entorno entorno) {
        TipoRetorno valor1 = exp1.jugar(entorno);
        TipoRetorno valor2 = exp2.jugar(entorno);
        if (valor1.tipo == Tipo.ENTERO || valor1.tipo == Tipo.DECIMAL) {
            if (valor2.tipo == Tipo.ENTERO || valor2.tipo == Tipo.DECIMAL) {
                return new TipoRetorno(Double.parseDouble(valor1.valor.toString()) < Double.parseDouble(valor2.valor.toString()), Tipo.BOOLEAN);
            }
        }
        return null;
    }

    private TipoRetorno mayorIgual(Entorno entorno) {
        TipoRetorno valor1 = exp1.jugar(entorno);
        TipoRetorno valor2 = exp2.jugar(entorno);
        if (valor1.tipo == Tipo.ENTERO || valor1.tipo == Tipo.DECIMAL) {
            if (valor2.tipo == Tipo.ENTERO || valor2.tipo == Tipo.DECIMAL) {
                return new TipoRetorno(Double.parseDouble(valor1.valor.toString()) >= Double.parseDouble(valor2.valor.toString()), Tipo.BOOLEAN);
            }
        }
        return null;
    }

    private TipoRetorno menorIgual(Entorno entorno) {
        TipoRetorno valor1 = exp1.jugar(entorno);
        TipoRetorno valor2 = exp2.jugar(entorno);
        if (valor1.tipo == Tipo.ENTERO || valor1.tipo == Tipo.DECIMAL) {
            if (valor2.tipo == Tipo.ENTERO || valor2.tipo == Tipo.DECIMAL) {
                return new TipoRetorno(Double.parseDouble(valor1.valor.toString()) <= Double.parseDouble(valor2.valor.toString()), Tipo.BOOLEAN);
            }
        }
        return null;
    }
}