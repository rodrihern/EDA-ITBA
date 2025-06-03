# 🌳 Árbol B – Estructura de Datos Multicamino

## 📌 ¿Qué es un Árbol B?

Un **Árbol B de orden N** es una estructura de árbol balanceado diseñada para funcionar eficientemente en sistemas que leen y escriben grandes bloques de datos (como bases de datos o sistemas de archivos).

### ✅ Propiedades

- Cada nodo contiene entre **N y 2N claves** (salvo la raíz).
- La raíz puede tener entre **1 y 2N claves**.
- Cada nodo con *k* claves tiene **k+1 hijos**.
- Todas las **hojas están al mismo nivel**.
- Las claves dentro de un nodo están **ordenadas**.

---

## 🌱 Inserción en un Árbol B

### 📋 Pasos

1. **Buscar la hoja** correcta donde debe ir la nueva clave.
2. **Insertar la clave** en orden.
3. Si el nodo tiene más de **2N claves**, se **divide en dos**:
   - La **clave del medio** sube al padre.
   - El nodo se parte en **dos hijos** con N claves cada uno.
4. Si el padre también excede las 2N claves → el **proceso se repite** recursivamente hasta la raíz.
5. Si la raíz se divide → se **crea una nueva raíz**.


---

## 🗑️ Borrado en Árbol B

### 📋 Reglas Generales

1. Si la clave está en una **hoja**, se elimina directamente.
2. Si está en un **nodo interno**, se **reemplaza** por su:
   - **Sucesor in-order** (el mínimo del subárbol derecho), o
   - **Predecesor in-order** (el máximo del subárbol izquierdo).
   Luego se elimina desde la hoja.

### 🔁 Rebalanceo tras borrar

Después de eliminar, si un nodo queda con **menos de N claves** (nodo "rojo"):

- **Se intenta pedir prestado** una clave a un hermano adyacente si este tiene > N claves.
- Si no es posible, se **fusiona** con el hermano y **baja** una clave del padre al nuevo nodo.
- Esto puede propagarse hacia arriba hasta la raíz.
- Si la raíz queda vacía, se elimina y el hijo fusionado pasa a ser la nueva raíz.

---

