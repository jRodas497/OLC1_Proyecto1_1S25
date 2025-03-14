package Clases.Utilidades;

import Clases.Abstractas.Expresion;
import Clases.Entorno.Entorno;
import Clases.Utilidades.*;

import java.util.Random;

public class RandomDecimal extends Expresion {
    private double value;

    public RandomDecimal() {
        super(TipoExpresion.PRIMITIVO);
        this.value = new Random().nextDouble();
    }

    @Override
    public TipoRetorno jugar(Entorno entorno) {
        return new TipoRetorno(value, Tipo.DECIMAL);
    }
}