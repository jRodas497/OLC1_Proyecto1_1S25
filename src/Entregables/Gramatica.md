# Gramatica [ BNF ] - Dilema del Prisionero

## Símbolo de Inicio

```
<INICIO> ::= <INSTRUCCIONES>
```

## Instrucciones Generales

```
<INSTRUCCIONES>     ::= <INSTRUCCIONES> <INSTRUCCION> | <INSTRUCCION>
<INSTRUCCION>       ::= <ESTRATEGIA> | <PARTIDA> | <PUNTO_ACCESO>
```

## Definición de Estrategias

```
<ESTRATEGIA>                ::= "strategy" <ID> "{" <INSTRUCCIONES_ESTRATEGIA> "}"
<INSTRUCCIONES_ESTRATEGIA>  ::= <DECISION_INICIAL> <REGLAS>
<DECISION_INICIAL>          ::= "initial:" <DECISION>
<REGLAS>                    ::= "rules:" "[" <LISTA_REGLAS> "]"
<LISTA_REGLAS>              ::= "if" <EXPRESION> "then" <EXPRESION> "," <LISTA_REGLAS> |
                                "if" <EXPRESION> "then" <EXPRESION> |
                                "else" <EXPRESION>
```

## Definición de Partidas

```
<PARTIDA>               ::= "match" <ID> "{" <INSTRUCCIONES_PARTIDA> "}"
<INSTRUCCIONES_PARTIDA> ::= <JUGADORES> <RONDAS> <PUNTUACIONES>
<JUGADORES>             ::= "players strategies:" "[" <ID> "," <ID> "]"
<RONDAS>                ::= "rounds:" <EXPRESION>
<PUNTUACIONES>          ::= "scoring:" "{" <PUNTOS> "}"
<PUNTOS>                ::= <COOPERACION_MUTUA> "," <DEFECCION_MUTUA> "," <TRAIDOR> "," <TRAICIONADO>
<COOPERACION_MUTUA>     ::= "cooperacionM:" <EXPRESION>
<DEFECCION_MUTUA>       ::= "defeccionnM:" <EXPRESION>
<TRAIDOR>               ::= "traidor:" <EXPRESION>
<TRAICIONADO>           ::= "traicionado:" <EXPRESION>
```

## Punto de Acceso

```
<PUNTO_ACCESO>          ::= "main" "{" <INSTRUCCIONES_ACCESO> "}"
<INSTRUCCIONES_ACCESO>  ::= "run" "[" <ID> "]" "with" "{" "seed:" <EXPRESION> "}"
```

## Expresiones

```
<EXPRESION>     ::= <RELACIONALES> | <LOGICAS> | <DECISION> | <FUNCIONES> | <LISTA> |
                "random" | "self_history" | "opponent_history" | "round_number" |
                "true" | "false" | <NUMERO>
<RELACIONALES>  ::= <EXPRESION> <OP_REL> <EXPRESION>
<OP_REL>        ::= "==" | "!=" | ">=" | "<=" | ">" | "<"
<LOGICAS>       ::= <EXPRESION> <OP_LOG> <EXPRESION> | "!" <EXPRESION>
<OP_LOG>        ::= "&&" | "||"
<FUNCIONES>     ::= "get_move(" <EXPRESION> "," <EXPRESION> ")" |
                "last_move(" <EXPRESION> ")" |
                "get_moves_count(" <EXPRESION> "," <EXPRESION> ")" |
                "get_last_n_moves(" <EXPRESION> "," <EXPRESION> ")"
<LISTA>         ::= "[" <ELEMENTOS> "]"
<ELEMENTOS>     ::= <ELEMENTOS> "," <EXPRESION> | <EXPRESION>
<DECISION>      ::= "C" | "D"
```

### Identificadores y Números

```
<ID>        ::= [a-zA-Z_][a-zA-Z0-9_]*
<NUMERO>    ::= [0-9]+ | [0-9]+ "." [0-9]+
```

### Explicación:

Se define una estrategia llamada NombreEstrategia.

La decisión inicial es D (Defect).

Se establecen reglas basadas en condiciones:
- Coopera (C) si el número de rondas es menor a 3.
- Defect (D) si el oponente ha jugado D más de 2 veces.
- En caso contrario, repite el último movimiento del oponente.

Ejemplo General de Partida

```
match NombrePartida {
    players strategies: [Estrategia1, Estrategia2]
    rounds: X
    scoring: {
        cooperacionM: Puntuacion_1,
        defeccionM: Puntuacion_2,
        traidor: Puntuacion_3,
        traicionado: Puntuacion_4
    }
}
```

#### Explicación:

Se define un enfrentamiento entre Estrategia1 y Estrategia2.
Se jugarán X rondas.
Se asignan puntuaciones según los distintos escenarios posibles.

### Ejemplo General del Punto de Acceso

```
main {
    run [NombrePartida] with {
        seed: 123
    }
}
Explicación:

Se ejecuta la partida NombrePartida.
Se usa una semilla aleatoria 123 para mantener consistencia en los resultados.
```

## Reglas de Puntuación

El sistema de puntuación debe cumplir con ciertas condiciones matemáticas que reflejan la naturaleza del dilema del prisionero:

```
T > R > P > S
```

Donde:
- **T (Tentación)**: betrayal reward - La recompensa por traicionar cuando el otro coopera
- **R (Recompensa)**: mutual cooperation - El beneficio por cooperación mutua
- **P (Castigo)**: mutual defection - La penalización por defección mutua
- **S (Tonto)**: betrayal punishment - El castigo por ser traicionado
