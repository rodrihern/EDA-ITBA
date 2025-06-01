package ExamenesViejos.P2_2023_2C;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AdjacencyListGraph<V, E> {

    private boolean isSimple;
    protected boolean isDirected;
    private boolean acceptSelfLoop;
    private boolean isWeighted;
    private Map<V, Collection<InternalEdge>> adjacencyList= new HashMap<>();

    protected   Map<V,  Collection<AdjacencyListGraph<V, E>.InternalEdge>> getAdjacencyList() {
        return adjacencyList;
    }
    public Collection<V> getVertices() {
        return getAdjacencyList().keySet() ;
    }

    protected AdjacencyListGraph(boolean isSimple, boolean isDirected, boolean acceptSelfLoop, boolean isWeighted) {
        this.isSimple = isSimple;
        this.isDirected = isDirected;
        this.acceptSelfLoop= acceptSelfLoop;
        this.isWeighted = isWeighted;
    }

    public int connectedComponentsQty() {
        if (isDirected) {
            throw new IllegalStateException("cannot call method in directed graph");
        }

        int count = 0;
        // Para marcar los vértices visitados
        java.util.Set<V> visited = new java.util.HashSet<>();

        for (V vertex : getVertices()) {
            if (!visited.contains(vertex)) {
                // BFS desde vertex
                java.util.Queue<V> queue = new java.util.LinkedList<>();
                queue.add(vertex);
                visited.add(vertex);

                while (!queue.isEmpty()) {
                    V current = queue.poll();
                    Collection<InternalEdge> neighbors = getAdjacencyList().get(current);
                    if (neighbors != null) {
                        for (InternalEdge edge : neighbors) {
                            V neighbor = edge.target;
                            if (!visited.contains(neighbor)) {
                                visited.add(neighbor);
                                queue.add(neighbor);
                            }
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }

    public void addVertex(V aVertex) {

        if (aVertex == null )
            throw new IllegalArgumentException("addVertexParamCannotBeNull");

        // no edges yet
        getAdjacencyList().putIfAbsent(aVertex, new ArrayList<InternalEdge>());
    }

    public void addEdge(V aVertex, V otherVertex, E theEdge) {

        // validation!!!!
        if (aVertex == null || otherVertex == null || theEdge == null)
            throw new IllegalArgumentException("addEdgeParamCannotBeNull");

        // es con peso? debe tener implementado el metodo double getWeight()
        if (isWeighted) {
            // reflection
            Class<? extends Object> c = theEdge.getClass();
            try {
                c.getDeclaredMethod("getWeight");
            } catch (NoSuchMethodException | SecurityException e) {
                throw new RuntimeException( "Graph is weighted but the method double getWeighed() is not declared in theEdge");
            }
        }

        if (! acceptSelfLoop && aVertex.equals(otherVertex)) {
            throw new RuntimeException(String.format("Graph does not accept self loops between %s and %s" ,
                    aVertex, otherVertex) );
        }

        // if any of the vertex is not presented, the node is created automatically
        addVertex(aVertex);
        addVertex(otherVertex);
    }

    class InternalEdge {
        E edge;
        V target;

        InternalEdge(E propEdge, V target) {
            this.target = target;
            this.edge = propEdge;
        }
    }
}
