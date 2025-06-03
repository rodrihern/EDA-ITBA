# Hashing

## ¿Qué es una Tabla de Hashing?

- Estructura que almacena pares **(key, value)** en un arreglo mediante una **función de hash**.
- Prioriza la **búsqueda rápida (O(1))**.
- No mantiene orden ni contigüidad entre elementos.

---

## Función de Hash

- `hash: Ω → [0, |Lookup|-1]`
- Asigna una posición en la tabla a cada clave.
- Se busca que disperse bien y minimice colisiones.
- Una **función hash perfecta** no genera colisiones, pero es difícil de lograr en la práctica.

---

## Colisiones y cómo resolverlas

### Tipos de Hashing

| Estrategia         | Alias                        | Colisiones se guardan en...                  |
|--------------------|------------------------------|----------------------------------------------|
| Open Addressing    | Closed Hashing               | Dentro de la misma tabla                     |
| Closed Addressing  | Open Hashing / Chaining      | Fuera de la tabla (listas, etc)             |

---

## Closed Hashing (Open Addressing)

- **Insertar**: si ranura ocupada → probar siguientes (rehasheo lineal o cuadrático).
- **Buscar**: continuar hasta encontrar o llegar a baja física.
- **Eliminar**:
  - Baja lógica: se marca como no existente.
  - Baja física: se elimina si la siguiente está vacía.

### Rehasheo

- Lineal: `i, i+1, i+2, ...` (tratado como lista circular).
- Cuadrático: `i, i+1², i+2², ...` (puede no encontrar lugar).
- Se duplica la tabla si se supera el **factor de carga**.

---

## Open Hashing (Closed Addressing / Chaining)

- Cada ranura puede tener una lista de elementos que colisionaron.
- **InsertOrUpdate**:
  - Si no hay lista → se crea y se inserta.
  - Si existe → se actualiza o inserta.
- **Remove**: si no hay lista → no existe. Si hay → se busca y borra.
- **Find**: se navega la lista para buscarlo.

---

## Factor de carga

- `loadFactor = usedKeys / tableSize`
- Si el loadFactor supera el threshold (e.g. 0.75), se **duplica** la tabla y se **rehashean** los elementos.

---

## Estrategias de Hashing para claves numéricas

- **División (mod m)**: elegir `m` primo.
- **Mid-square**: cuadrado de X y tomar los dígitos del medio.
- **Folding (Plegado)**: sumar grupos de dígitos.
- **Análisis del dígito**: elegir partes de la clave menos repetitivas.

---

## Hashing en Java (HashMap desde Java 8)

- Usa un arreglo con zonas de overflow.
- Si una ranura está muy cargada, **convierte la lista en un árbol rojo-negro**.
- Las claves no comparables se ordenan por su `hashCode()`.

---

## Consideraciones de implementación

- Redefinir `.equals()` correctamente si se usan listas para comparación.
- Usar `LinkedList` en Java para las zonas de overflow.
- Algunas mejoras avanzadas:
  - Factor de carga **global** vs. **local** (para listas muy largas).
  - Dividir listas largas individualmente (split).

---

## Casos de uso típicos

- Lookup rápido en parsers, caching, fingerprinting (como Shazam).
- Ideal cuando no importa el orden de los elementos y se prioriza velocidad.

