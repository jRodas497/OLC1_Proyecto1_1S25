package Clases.Utilidades;

import java.util.Random;

public class RandomDecimal {
    private double value;

    public RandomDecimal() {
        this.value = new Random().nextDouble();
    }

    public double getValue() {
        return value;
    }
}