package Classes.Objects;

import Classes.Instruction.InstruccionEstrategia;

public class Estrategia {
    public String nombre;
    public InstruccionEstrategia instrucciones;

    public Estrategia(String nombre, InstruccionEstrategia instrucciones) {
        this.nombre = nombre;
        this.instrucciones = instrucciones;
    }
}