package Classes.Instruction;

import Classes.Abstract.Instruccion;
import Classes.Around.Entorno;
import Classes.Objects.Estrategia;
import Classes.Utility.TipoInstruccion;

public class CrearEstrategia extends Instruccion {
    String nombre;
    InstruccionEstrategia instrucciones;
    public CrearEstrategia(String nombre, InstruccionEstrategia instrucciones) {
        super(TipoInstruccion.ESTRATEGIA);
        this.nombre = nombre;
        this.instrucciones = instrucciones;
    }

    public void jugar(Entorno entorno) {
        Estrategia estrategia = new Estrategia(nombre, instrucciones);
        entorno.guardarEstrategia(nombre, estrategia);
        System.out.println("guardó la estrategia");
    }
}