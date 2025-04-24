# Respuesta


El score de cada documento en Lucene se calcula utilizando el modelo de espacio vectorial, basado en **TF-IDF** (Term Frequency - Inverse Document Frequency) y la **similitud de coseno**. A continuación, te explico cómo se arma el score:

---

## 📐 Fórmula del Score

El score de un documento (`d`) para una consulta (`q`) se calcula como:

$$$
score(q, d) = \sum[ tf(t, d) \cdot  idf(t)^2 ] 
$$$
para cada término t en la consulta q


Donde:
- `t` es un término de la consulta.
- `tf(t, d)` es la frecuencia del término `t` en el documento `d`.
- `idf(t)` es la frecuencia inversa de documentos: $idf(t) = 1 + log(N / df(t))$  
- `N` es el número total de documentos.
- `df(t)` es el número de documentos que contienen el término `t`.

---

## 🔢 Pasos para calcular el score

1. **Calcular `df(t)`**: cuántos documentos contienen el término `t`.
2. **Calcular `idf(t)`**: usando la fórmula anterior.
3. **Calcular `tf(t, d)`**: cuántas veces aparece el término `t` en el documento `d`.
4. **Sumar contribuciones**: para cada término, se calcula su contribución al score total del documento.

---

## 📊 Ejemplo práctico

Consulta: `content:Fly OR content:Moon`

### Documentos indexados:

| Documento | Términos indexados                  |
|-----------|-------------------------------------|
| a.txt     | fly, me, to, the, moon              |
| b.txt     | fly, fly, away                      |
| c.txt     | bohemian, rhapsody                  |
| d.txt     | to, the, moon, and, back            |
| e.txt     | fly, like, an, eagle                |

---

### 1. Cálculo de `df(t)`:

- `df(fly) = 3` → (a.txt, b.txt, e.txt)
- `df(moon) = 2` → (a.txt, d.txt)

---

### 2. Cálculo de `idf(t)` (con N = 5):

- `idf(fly) = 1 + log(5 / 3) ≈ 1.22`
- `idf(moon) = 1 + log(5 / 2) ≈ 1.40`

---

### 3. Frecuencia `tf(t, d)` por documento:

| Documento | tf(fly) | tf(moon) |
|-----------|---------|----------|
| a.txt     | 1       | 1        |
| b.txt     | 2       | 0        |
| c.txt     | 0       | 0        |
| d.txt     | 0       | 1        |
| e.txt     | 1       | 0        |

---

### 4. Cálculo del score:

- `a.txt`:  
  `score = 1 × 1.22² + 1 × 1.40² = 1.49 + 1.96 = 3.45`

- `b.txt`:  
  `score = 2 × 1.22² = 2 × 1.49 = 2.98`

- `c.txt`:  
  `score = 0` (no contiene fly ni moon)

- `d.txt`:  
  `score = 1 × 1.40² = 1.96`

- `e.txt`:  
  `score = 1 × 1.22² = 1.49`

---

## 📈 Resultado final

Ordenados por score de mayor a menor:

1. `a.txt`: score = **3.45**
2. `b.txt`: score = **2.98**
3. `d.txt`: score = **1.96**
4. `e.txt`: score = **1.49**
5. `c.txt`: score = **0**

---
