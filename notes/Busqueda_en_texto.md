# Resumen de Algoritmos de Comparación de Strings

## Soundex
- Algoritmo fonético: codifica palabras según cómo suenan.
- Devuelve **siempre 4 caracteres**: 1 letra + 3 dígitos.
- Proceso:
    1. Convertir a mayúsculas y eliminar no-letras (opcional).
    2. OUT[0] = IN[0].
    3. Guardar peso fonético de IN[0] como `last`.
    4. Recorrer las siguientes letras:
        - Calcular peso `current`.
        - Si `current` ≠ 0 y `current` ≠ `last`, agregarlo a OUT.
        - Actualizar `last = current`.
    5. Completar con ceros si faltan dígitos.

| Letra                  | Peso               |
|------------------------|--------------------|
| A, E, I, O, U, Y, W, H | 0 (no se codifica) |
| B, F, P, V             | 1                  |
| C, G, J, K, Q, S, X, Z | 2                  |
| D, T                   | 3                  |
| L                      | 4                  |
| M, N                   | 5                  | 
| R                      | 6                  |

- **Comparación:** proporción de caracteres coincidentes entre codificaciones.

## Metaphone
- Codifica palabras fonéticamente en símbolos de **longitud variable**.
- **Comparación:** proporción de caracteres coincidentes respecto a la **máxima** longitud de los encodings.

## Levenshtein Distance
- Mide la cantidad mínima de operaciones (insertar, borrar, sustituir) para transformar un string en otro.
- Es una **distancia** (no una similitud).
- **Propiedades**:
    - Simetría.
    - Desigualdad triangular.

- **Algoritmo (Programación Dinámica)**:
    1. Crear matriz de distancias.
    2. Para cada celda:
        - Costo = 0 si caracteres iguales, 1 si diferentes.
        - Valor = mínimo entre:
            - Diagonal + costo (sustitución).
            - Arriba + 1 (eliminación).
            - Izquierda + 1 (inserción).
    3. Resultado = valor en la celda inferior derecha.

- **Operaciones según la tabla**:
    - Sustitución: diagonal + 1.
    - Inserción: izquierda + 1.
    - Eliminación: arriba + 1.

### Formula normalizada de distancia
$$
1 - \frac{Levenshtein(s1, s2)}{max(str1.len, str2.len)}
$$

## Q-Grams (N-Grams)
- Divide un string en subcadenas de longitud Q.
- Ejemplo: para "JOHN" y Q=3 → trigrams de "##JOHN##".
- **Comparación**: cantidad de Q-gramas compartidos.
- Fórmula para similitud:
    - Basada en trigramas generados y trigramas no compartidos.

$$
\frac {TG(str1) + TG(str2) - TGNS(str1, str2)} {TG(str1) + TG(str2)}
$$
donde:
* TG(str): cantidad de trigramas que se generaron en el string str
* TGNS(str1, str2): cantidad que no matchearon

## Fuerza Bruta (Naive)
- Compara el string completo recorriendo carácter por carácter.
- Cuando hay mismatch, vuelve atrás en query y target.
- **Complejidades**:
    - Temporal: O(n.m) (peor caso).
    - Espacial: O(1).

## Knuth-Morris-Pratt (KMP)
- Evita retrocesos innecesarios usando preprocesamiento del patrón.
- **Preprocesamiento**:
    - Construir tabla `Next`, donde cada posición guarda el tamaño del borde propio más grande.
- **Búsqueda**:
    - Si match: avanzar ambos punteros.
    - Si mismatch: usar `Next` para determinar el nuevo lugar del patrón.
- **Complejidad**:
    - O(n + m) temporal, donde n = longitud del texto, m = longitud del patrón.

---

