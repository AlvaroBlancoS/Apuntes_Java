# Apuntes_Java
- [Condición if](#condición-if)
- [Condición else](#condición-else)
- [Condición else if](#condición-else-if)
- [Operadores comunes en condiciones](#operadores-comunes-en-condiciones)
- [Condición switch](#condición-switch)
- [Bucle for](#bucle-for)
- [Bucle foreach](#bucle-foreach)
- [Bucle while](#bucle-while)
- [Bucle do while](#bucle-Do-While)
- [Entre while y wo while](#entre-while-y-do-while)
- [Stream](#stream)

## Condición if
- **[if](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Conditions/BasicCondition.java)**: Ejecuta un bloque de código si la condición es verdadera

## Condición else
- **[else](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Conditions/ConditionWithElse.java)**: Ejecuta un bloque si la condición es verdadera y otra es falsa

## Condición else if
- **[else if](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Conditions/ConditionWithElseIf.java)**: Permite evaluar múltiples en orden

## Condición switch
- **[swith](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Conditions/ConditionSwitch.java)**: Ideal cuando se compran valores concretos de una misma variable
- **[swith](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Conditions/ConditionSwitchV2.java)**: La función es lo mismo que el anterior pero el código se ve más limpio

## Operadores comunes en condiciones
- **==** : Igual a  
- **!=** : Diferente de  
- **>**  : Mayor que  
- **<**  : Menor que  
- **>=** : Mayor o igual que  
- **<=** : Menor o igual que  
- **&& (and)/ || (or)/ ! (not)** : Operadores lógicos
- [Primer lógico](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Conditions/LogicV1.java)
- [Segundo lógico](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Conditions/LogicV2.java)
  
## Bucle for
- **[for V1](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Loops/ForV1.java)**: Se usa cuando sabemos cuántas veces queremos repetir algo. Este incrementa de 1 a 5
- **[for V2](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Loops/ForV2.java)**:  Es útil de recorrer colecciones o arreglos
- **[for V3](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Loops/ForV3.java)**: Lee los números pares e impares
- **[Control de bucles](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Loops/LoopControl.java)**:
  * `break` => Sale del bucle completamente
  * `While` => Salta a la siguiente iteración
## Bucle foreach
- **[Foreach V1](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Loops/ForEachV1.java)**: También se recorre colecciones o arreglos pero es más fácil de usar

## Bucle while
- **[While V1](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Loops/WhileV1.java)**:Eejcuta un bloque mientas la condición sea verdadera
- **[While V2](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Loops/WhileV2.java)**:Curiosamente se puede recorrer colecciones o arreglaos mientas la condición sea verdadera
- **[While V3](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Loops/WhileV3.java)**: Lee los números pares e impares mientas la condición sea verdadera
- **[While V4](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Loops/WhileV4.java)**: Introducir de un numéro mayor que x

## Bucle do while
- **[Do While V1](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Loops/DoWhileV1.java)**: Similar a `While`, pero garantiza al menos una ejecución antes de comprobar la condición
- **[Do While V2](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Loops/DoWhileV2.java)**:También puede recorre colecciones o arreglos, pero garantiza al menos una ejecución antes de comprobar la condición
  
## Entre while y do while
- **[Entre While y Do While](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/learning/control_structures/Loops/WhiteAndDoWhile.java)**: Es un buen ejemplo para entender la diferencia
- `While`
  - Es un bucle de comprobación previa (evalúa antes de ejecutar):
    * Primero comprueba la condición -> si es `true`, ejecuta el bloque.
    * Si es `false` desde el inicio, ni siquiera entra.
  - iDEAL PARA:
    * Validaciones previas (Solo entras su ya cumples algo).
    * Procesos que pueden no ejecutarse nunca.
    * Ejemplo clásico: recorrer una lista mientras haya elementos.
  - En lógica: "Mientas la condición sea verdadera, sigue ejecutando"
- `Do While`
  - Es un bucle de comprobacción posterior (evalúa después de ejecutar):
    * Primero ejecuta, luego comprueba la condición.
    * Se ejecuta al menos una vez, aunque la condición sea falsa al inicio.
  - iDEAL PARA:
    * Menús interactivos.
    * Validaciones de entrada (pedir datos hasta que sean correctos).
    * Intentos de contraseña, confirmaciones, etc.
  - En lógica: "Hz esto al menos una vez, y repite mientras la condición sea verdadera"
 ## Stream
- **[Streamv1]()**: prueba
    

