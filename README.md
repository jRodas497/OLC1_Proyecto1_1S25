# Lenguaje Declarativo Minimalista para el Dilema del Prisionero - [ Proyecto 1 ]

## Objetivos
### - 1.1 Objetivos Generales
Aplicar los conocimientos sobre la fase de análisis léxico y sintáctico de un compilador para la construcción de una solución de software.
### - 1.2 Objetivos específicos
- Que el estudiante aprenda a generar analizadores léxicos y sintácticos utilizando las herramientas de JFLEX y CUP.
- Que el estudiante aprenda los conceptos de token, lexema, patrones y expresiones regulares.
- Que el estudiante pueda realizar correctamente el manejo de errores léxicos.
- Que el estudiante sea capaz de realizar acciones gramaticales utilizando el lenguaje de programación JAVA.

## Descripción
El curso de Organización de Lenguajes y Compiladores 1 desafía a los estudiantes a construir un sistema que capture la esencia de la evolución de estrategias computacionales, inspirado directamente en los revolucionarios experimentos de Robert Axelrod.
Comprenderán y aplicarán los torneos de estrategias que transforman nuestra comprensión de la cooperación. Deberán diseñar un lenguaje declarativo
que permita:
- Modelar estrategias complejas de interacción
- Simular múltiples rondas de decisiones estratégicas
- Analizar cómo emergen comportamientos cooperativos

El objetivo es que cada estudiante recree el proceso de Axelrod: desarrollar un sistema donde estrategias compiten, evolucionan y revelan principios fundamentales de interacción. No se trata solo de programar, sino de experimentar con modelos computacionales que reflejan comportamientos sociales y estratégicos complejos.

## Introducción
### - 3.1 ¿Cuál es el dilema del prisionero?
El Dilema del Prisionero es un concepto clásico de la teoría de juegos. Se plantea de la siguiente manera:
Dos personas son arrestadas bajo la sospecha de haber cometido un delito grave. Sin embargo, la policía no tiene pruebas suficientes para condenarlos por ese delito, así que los separan y les hacen la misma oferta
- Si uno confiesa y el otro guarda silencio, el que confiesa será liberado, mientras que el otro recibirá una sentencia máxima.
- Si ambos confiesan, ambos recibirán una sentencia intermedia.
- Si ninguno confiesa , ambos recibirán una sentencia menor por un delito menor.
  Se supone que ambos prisioneros son completamente egoístas y su única meta es reducir su propia estancia en la cárcel. Los prisioneros tienen dos opciones: cooperar con su cómplice y permanecer callado, o delatar y traicionar a su cómplice y confesar. El resultado de cada elección depende de la elección del cómplice. Por desgracia, **uno no conoce qué ha elegido hacer el otro hasta después de elegir qué hará y se dicte el resultado.**

![Figura 1. Posibles resultados del dilema](/Entregables/Imagenes/Tabla.png)

Los experimentos de Axelrod expandieron significativamente nuestra comprensión de este dilema. Mediante torneos computacionales donde diferentes estrategias competían entre sí, Axelrod demostró que la cooperación puede emerger incluso en sistemas aparentemente egoístas.

### - 3.2 Funciones del Lenguaje
Este lenguaje está diseñado para permitir a los usuarios definir y ejecutar estrategias en el contexto del dilema del prisionero. Es:
- Declarativo: Se enfoca en qué hacer mediante reglas y condiciones, no en cómo hacerlo paso a paso.
- Minimalista: Incluye únicamente los elementos esenciales para su funcionamiento.
  En él, se definen estrategias, partidas y un punto de entrada que orquesta la ejecución.

## Estructura del Lenguaje
El lenguaje consta de tres secciones principales:
- **Definición de Estrategias**
- **Definición de Partidas**
- **Punto de Entrada**

## Requerimientos Mínimos
Para que el estudiante tenga derecho a calificación, deberá cumplir con lo siguiente:
- Carga de archivos
- Analizador Léxico y Sintáctico
- Salidas en consola
- Documentación completa
## Entregables
- **Código fuente del proyecto**
- **Archivo Ejecutable (JAR)**
- **Último Hash de git**
- **Manual de Usuario en un archivo Markdown**
    - Capturas de pantalla detallando cómo funciona su entorno de trabajo y los reportes que se generan.
- **Manual Técnico en un archivo Markdown**
    - Información importante del proyecto para que se pueda realizar el mantenimiento en el futuro. Especificar el lenguaje, herramientas utilizadas, métodos y funciones más importantes.
- **Archivo de Gramática en un archivo Markdown**
    - El archivo debe contener su gramática y debe de ser limpio, entendible y no debe ser una copia del archivo de Cup.
    - La gramática debe estar escrita en formato BNF(Backus-Naur form).

## Restricciones
- La entrega debe ser realizada mediante UEDI enviando el enlace del **repositorio privado** de Gitlab en donde se encuentra su proyecto.
- El nombre del repositorio de Github debe ser **OLC1_Proyecto1_#Carnet**
- Se debe agregar al auxiliar encargado como colaborador al repositorio de Gitlab, con permisos de desarrollador (Developer).
    - Sección B (Diego Facundo Pérez): **DFaxx**
    - Sección C (Brandon Tejaxun): **brandonT2002**
    - Sección N (Xhunik Miguel): **xhuniktzi**
- Lenguaje de Programación a utilizar: Java
- Herramientas para el análisis léxico y sintáctico: JFlex/Cup
- **El proyecto debe ser realizado de forma individual.**
- **Copias completas/parciales** de: código, gramática, etc. serán merecedoras de una **nota de 0 puntos**, los responsables serán reportados al catedrático de la sección y a la Escuela de Ciencias y Sistemas.
- La calificación tendrá una duración de 30 minutos, acorde al programa del laboratorio.

## SE LE CALIFICARA DEL ÚLTIMO COMMIT REALIZADO ANTERIOR A ESTA FECHA.