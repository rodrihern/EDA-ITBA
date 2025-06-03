# 🌐 Grafos – Resumen General

## 📌 Definición
Un **grafo** es una estructura compuesta por:
- Un conjunto de **vértices** (nodos)
- Un conjunto de **aristas** (ejes) que conectan pares de vértices

---

## 🔁 Tipos de grafos

| Tipo           | Dirigido | Múltiples aristas | Lazos | Peso |
|----------------|----------|-------------------|-------|------|
| Simple         | ❌/✅     | ❌                | ❌    | ❌/✅ |
| Multigrafo     | ❌/✅     | ✅                | ❌    | ❌/✅ |
| Pseudografo    | ❌/✅     | ✅                | ✅    | ❌/✅ |

**📌 Nota**: 
- "Dirigido" → ejes tienen sentido (como una flecha).
- "Lazo" → eje que conecta un nodo consigo mismo.

---

## 🏗️ Representaciones

1. **Matriz de adyacencia**:  
   - Tamaño `n × n` (n = número de nodos)
   - Ideal para grafos densos
   - `adj[i][j] = 1` si hay un eje de `i → j`

2. **Lista de adyacencia**:  
   - Ideal para grafos esparcidos
   - Map o array donde cada nodo tiene una lista de sus vecinos

3. **Matriz/lista de incidencia**:  
   - Usada para representar explícitamente aristas

---

## 🚶‍♂️ Recorridos en Grafos

### 🔵 BFS (Breadth-First Search)
- Visita los nodos por **niveles**
- Utiliza una **cola (Queue)** y un **Set de visitados**

```java
public void bfs(Node start) {
    Set<Node> visited = new HashSet<>();
    Queue<Node> queue = new LinkedList<>();

    visited.add(start);
    queue.add(start);

    while (!queue.isEmpty()) {
        Node current = queue.poll();
        System.out.println(current);

        for (Node neighbor : current.getNeighbors()) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                queue.add(neighbor);
            }
        }
    }
}
```

### 🔵 DFS (Depth-First Search)
- Visita los nodos por **profundidad**
- Utiliza un **stack** y un **Set de visitados**

```java
public void dfs(Node start) {
    Set<Node> visited = new HashSet<>();
    Stack<Node> stack = new Stack<>();

    stack.push(start);

    while (!stack.isEmpty()) {
        Node current = stack.pop();
        if (!visited.contains(current)) {
            visited.add(current);
            System.out.println(current);

            for (Node neighbor : current.getNeighbors()) {
                stack.push(neighbor);
            }
        }
    }
}

```


