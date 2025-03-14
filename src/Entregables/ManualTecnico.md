# Manual Técnico

## Introducción
Este documento proporciona información detallada sobre el sistema, incluyendo su estructura, herramientas utilizadas y metodologías clave. Sirve como referencia para el mantenimiento y desarrollo futuro del proyecto.

## Lenguaje y Herramientas Utilizadas

- **Lenguaje de Programación**: Java
- **Herramientas**:
    - CUP (Constructor de Analizadores Sintácticos)
    - JFlex (Generador de Analizadores Léxicos)
    - Estructuras de datos en Java (Listas, Mapas, Pilas, etc.)
    - Algoritmos de caminos más cortos para análisis de estrategias

## Arquitectura del Sistema

El sistema se divide en los siguientes módulos:

1. **Análisis Léxico**: Responsable de tokenizar el código fuente utilizando JFlex.
2. **Análisis Sintáctico**: Construcción del árbol sintáctico utilizando CUP.
3. **Ejecución de Estrategias**: Evaluación de las estrategias según las reglas definidas en la gramática.
4. **Simulación de Partidas**: Implementación de rondas de interacción según las estrategias definidas.

## Definición de Estrategias

Las estrategias en el sistema se definen mediante una estructura de reglas condicionales. Cada estrategia contiene:
- Un estado inicial (C o D)
- Un conjunto de reglas que determinan la decisión en cada ronda

Ejemplo:
```
strategy Graaskamp {
    initial: D
    rules: [
        if round_number <= 2 then D,
        if round_number == 3 && get_moves_count(opponent_history, D) == 2 then C,
        if round_number > 3 && get_last_n_moves(opponent_history, 2) == [D, D] then D,
        else last_move(opponent_history)
    ]
}
```

## Definición de Partidas

Las partidas se configuran con:
- Estrategias participantes
- Cantidad de rondas
- Sistema de puntuación

Ejemplo:
```
match GraaskampvsRandom {
    players strategies: [Graaskamp, Random]
    rounds: 100
    scoring: {
        mutual cooperation: 3,
        mutual defection: 1,
        betrayal reward: 5,
        betrayal punishment: 0
    }
}
```

## Sistema de Puntuación
El sistema de puntuación cumple con las condiciones del dilema del prisionero:

```
T > R > P > S
```

Donde:
- **T (Tentación)**: betrayal reward - La recompensa por traicionar cuando el otro coopera.
- **R (Recompensa)**: mutual cooperation - El beneficio por cooperar mutuamente.
- **P (Castigo)**: mutual defection - Penalización por defectar mutuamente.
- **S (Tonto)**: betrayal punishment - Castigo por ser traicionado.

## Punto de Acceso

El punto de acceso principal está definido en `main`, donde se ejecutan las partidas:
```
main {
    run [GraaskampvsRandom] with {
        seed: 42
    }
}
```

## Principales Métodos y Funciones

- `get_move(historial, ronda)`: Obtiene la jugada de un jugador en una ronda específica.
- `last_move(historial)`: Devuelve la última jugada de un jugador.
- `get_moves_count(historial, decision)`: Cuenta cuántas veces se ha realizado una decisión.
- `get_last_n_moves(historial, n)`: Retorna las últimas `n` jugadas de un jugador.

## Consideraciones Finales

Este manual técnico debe actualizarse conforme se realicen cambios en el sistema. Se recomienda documentar cada nueva función y ajuste en la estructura de datos.

