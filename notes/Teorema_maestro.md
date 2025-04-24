# Teorema Maestro - Análisis de Algoritmos Recursivos

El **Teorema Maestro** es una herramienta poderosa para analizar la complejidad de algoritmos recursivos que siguen este patrón:

## 📌 Fórmula General

$$
T(N) = a \cdot T\left(\frac{N}{b}\right) + c \cdot N^d
$$

Esta fórmula aparece cuando tu algoritmo:

1. **Divide** el problema en **"a" subproblemas** de tamaño reducido.
2. **Resuelve** cada subproblema recursivamente.
3. **Combina** las soluciones con un costo de $$c \cdot N^d$$.

---

## 📘 Parámetros

- **a**: Número de subproblemas generados en cada llamada recursiva.
- **b**: Factor por el cual se reduce el tamaño del input.
- **N**: Tamaño de entrada.
- **d**: Exponente que representa el costo del trabajo fuera de la recursión.
- **c**: Constante positiva (no afecta el análisis asintótico).

---

## 📘 Casos del Teorema Maestro

### 📍 Caso 1: $$a < b^d$$

El trabajo no recursivo domina.  
**Complejidad:**

$$
T(N) = \mathcal{O}(N^d)
$$

---

### 📍 Caso 2: $$a = b^d$$

El trabajo está balanceado entre recursión y combinación.  
**Complejidad:**

$$
T(N) = \mathcal{O}(N^d \log N)
$$

---

### 📍 Caso 3: $$a > b^d$$

Las llamadas recursivas dominan.  
**Complejidad:**

$$
T(N) = \mathcal{O}(N^{\log_b a})
$$

---

## ⚠️ Cuándo NO se puede usar

Un ejemplo es la serie de Fibonacci recursiva:

```java
int fibo(int N) {
    if (N <= 1)
        return N;
    return fibo(N - 1) + fibo(N - 2);
}
```

No se puede usar pues **no** se divide el problema en N/b

---

## 🧪 Ejemplo: MergeSort

````java
void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right); // combina en O(n)
    }
}
````

* Se divide en 2 subproblemas $\rightarrow a=2$
* Cada subproblema tiene tamaño N/2 $rightarrow b=2$
* El trabajo de merge es lineal $\rightarrow d=1$

luego $b^d=2^1=2=a$

$$
\therefore es \space O(NlogN)
$$




















