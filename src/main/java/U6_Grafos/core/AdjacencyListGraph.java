package U6_Grafos.core;


import U6_Grafos.core_service.GraphService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import java.util.Map;


abstract public class AdjacencyListGraph<V, E> implements GraphService<V, E> {

    private boolean isSimple;
    protected boolean isDirected;
    protected boolean acceptSelfLoop;
    private boolean isWeighted;
    protected String type;

    // HashMap no respeta el orden de insercion. En el testing considerar eso
    private Map<V, Collection<InternalEdge>> adjacencyList = new HashMap<>();

    // respeta el orden de llegada y facilita el testing
    //	private Map<V,Collection<InternalEdge>> adjacencyList= new LinkedHashMap<>();

    protected Map<V, Collection<AdjacencyListGraph<V, E>.InternalEdge>> getAdjacencyList() {
        return adjacencyList;
    }


    protected AdjacencyListGraph(boolean isSimple, boolean isDirected, boolean acceptSelfLoop, boolean isWeighted) {
        this.isSimple = isSimple;
        this.isDirected = isDirected;
        this.acceptSelfLoop = acceptSelfLoop;
        this.isWeighted = isWeighted;

        this.type = String.format("%s %sWeighted %sGraph with %sSelfLoop",
                isSimple ? "Simple" : "Multi", isWeighted ? "" : "Non-",
                isDirected ? "Di" : "", acceptSelfLoop ? "" : "No ");
    }


    @Override
    public String getType() {
        return type;
    }

    @Override
    public void addVertex(V aVertex) {

        if (aVertex == null)
            throw new IllegalArgumentException(Messages.getString("addVertexParamCannotBeNull"));

        // no edges yet
        getAdjacencyList().putIfAbsent(aVertex, new ArrayList<InternalEdge>());
    }


    @Override
    public int numberOfVertices() {
        return getVertices().size();
    }

    @Override
    public Collection<V> getVertices() {
        return getAdjacencyList().keySet();
    }

    @Override
    public int numberOfEdges() {
        int numberOfEdges = 0;

        for (Collection<InternalEdge> col : adjacencyList.values()) {
            numberOfEdges += col.size();
        }

        // En grafos no dirigidos, cada arista se cuenta dos veces
        return isDirected ? numberOfEdges : numberOfEdges / 2;
    }


    @Override
    public void addEdge(V aVertex, V otherVertex, E theEdge) {

        // validation!!!!
        if (aVertex == null || otherVertex == null || theEdge == null)
            throw new IllegalArgumentException(Messages.getString("addEdgeParamCannotBeNull"));

        // es con peso? debe tener implementado el metodo double getWeight()
        if (isWeighted) {
            // reflection
            Class<?> c = theEdge.getClass();
            try {
                c.getDeclaredMethod("getWeight");
            } catch (NoSuchMethodException | SecurityException e) {
                throw new RuntimeException(
                        type + " is weighted but the method double getWeighed() is not declared in theEdge");
            }
        }

        if (!acceptSelfLoop && aVertex.equals(otherVertex)) {
            throw new RuntimeException(String.format("%s does not accept self loops between %s and %s",
                    type, aVertex, otherVertex));
        }

        // if any of the vertex is not presented, the node is created automatically
        addVertex(aVertex);
        addVertex(otherVertex);


    }


    @Override
    public boolean removeVertex(V aVertex) {
        if(aVertex == null)
            throw new RuntimeException("Vertex is null");

        if(adjacencyList.get(aVertex) == null)
            return false;

        if(isDirected){
            adjacencyList.remove(aVertex);
            for(Map.Entry<V, Collection<InternalEdge>> entry : adjacencyList.entrySet()) {
                entry.getValue().removeIf(edge -> edge.target.equals(aVertex));
            }
        }
        else {
            for(InternalEdge edge : adjacencyList.get(aVertex)) {
                if(!edge.target.equals(aVertex)) {
                    adjacencyList.get(edge.target).removeIf(otherEdge -> otherEdge.target.equals(aVertex));
                }
            }
            adjacencyList.remove(aVertex);
        }
        return true;
    }

    @Override
    public boolean removeEdge(V aVertex, V otherVertex) {
        if (aVertex == null || otherVertex == null) {
            throw new IllegalArgumentException(Messages.getString("removeEdgeParamCannotBeNull"));
        }

        if (!adjacencyList.containsKey(aVertex) || !adjacencyList.containsKey(otherVertex)) {
            return false; // Uno o ambos vértices no existen, por lo tanto no hay arista que remover.
        }

        boolean edgeWasRemoved = false;

        Collection<InternalEdge> edgesFromA = adjacencyList.get(aVertex);
        if (edgesFromA != null) {
            if (edgesFromA.removeIf(internalEdge -> internalEdge.target.equals(otherVertex))) {
                edgeWasRemoved = true;
            }
        }

        if (!isDirected) {
            Collection<InternalEdge> edgesFromB = adjacencyList.get(otherVertex);
            if (edgesFromB != null) {
                // Si aVertex.equals(otherVertex), edgesFromA y edgesFromB son la misma colección.
                // removeIf se comportará correctamente, no eliminando dos veces si ya se fue.
                if (edgesFromB.removeIf(internalEdge -> internalEdge.target.equals(aVertex))) {
                    edgeWasRemoved = true;
                }
            }
        }
        
        return edgeWasRemoved;
    }

    @Override
    public boolean removeEdge(V aVertex, V otherVertex, E theEdge) {
        if (!adjacencyList.containsKey(aVertex) || !adjacencyList.containsKey(otherVertex)) {
            throw new IllegalArgumentException("hola manola");
        }
		boolean res = adjacencyList.get(aVertex).remove(new InternalEdge(theEdge, otherVertex));
        if (!isDirected) {
             adjacencyList.get(otherVertex).remove(new InternalEdge(theEdge, aVertex));
        }

        return res;
    }


    @Override
    public void dump() {
        // COMPLETAR
        throw new RuntimeException("not implemented yet");
    }


    @Override
    public int degree(V aVertex) {
        //Si es dirigido, no podemos hablar de degree
        if(isDirected)
            throw new RuntimeException("directed graph cannot call this method");

        if(aVertex == null)
            throw new RuntimeException("vertex cannot be null");

        if(adjacencyList.get(aVertex) == null)
            throw new RuntimeException("vertex not found");

        return adjacencyList.get(aVertex).size();
    }

    @Override
    public int inDegree(V aVertex) {
        //Solo para dirigidos
        if(!isDirected)
            throw new RuntimeException("graph that isn't directed, cannot call this method");

        if(aVertex == null)
            throw new RuntimeException("vertex cannot be null");

        int degree = 0;

        for (Collection<InternalEdge> col : adjacencyList.values()) {
            for (InternalEdge edge : col) {
                if (edge.target.equals(aVertex)) {
                    degree++;
                }
            }
        }

        return degree;
    }

    @Override
    public int outDegree(V aVertex) {
        //Solamente para dirigidos
        if(!isDirected)
            throw new RuntimeException("graph that isn't directed cannot call this method");

        if(aVertex == null)
            throw new RuntimeException("vertex cannot be null");

        if(adjacencyList.get(aVertex) == null)
            throw new RuntimeException("vertex not found");

        return adjacencyList.get(aVertex).size();
    }

    // Dijsktra exige que los pesos sean positivos!!!
    public abstract DijkstraPath<V, E> dijsktra(V source);

    public abstract void printAllPaths(V startNode, V endNode);


    class InternalEdge {
        E edge;
        V target;

        InternalEdge(E propEdge, V target) {
            this.target = target;
            this.edge = propEdge;
        }

        @Override
        public boolean equals(Object obj) {
            @SuppressWarnings("unchecked")
            InternalEdge aux = (InternalEdge) obj;

            return ((edge == null && aux.edge == null) || (edge != null && edge.equals(aux.edge)))
                    && target.equals(aux.target);
        }

        @Override
        public int hashCode() {
            return target.hashCode();
        }

        @Override
        public String toString() {
            return String.format("-[%s]-(%s)", edge, target);
        }
    }


}

