package ExamenesViejos.P2_nica.Ej2;


import java.util.Collection;
import java.util.Map;

import ExamenesViejos.P2_nica.*;


public class SimpleOrDefault<V,E> extends AdjacencyListGraph<V,E> {

	public SimpleOrDefault(boolean isDirected, boolean acceptSelfLoops, boolean isWeighted) {
		super(true, isDirected, acceptSelfLoops, isWeighted);
	
	}

	
	@Override
	public double getLocalClusteringCoeff(V aVertex) {
		if ( ! existsVertex(aVertex))
			throw new RuntimeException("el vertice no existe en el grafo");
		
		if ( ! isSimple ||  isDirected || acceptSelfLoop )
			throw new RuntimeException("tiene que ser simple, sin lazos y no dirigido");
		

		Map<V, Collection<InternalEdge>> adjList = getAdjacencyList();
		Collection<InternalEdge> edges = adjList.get(aVertex);
		int degree = edges.size();
		if (degree <= 1) {
			return -1;
		}
		int potentialTriangles = degree * (degree-1) / 2;
		int triangles = 0;
		for (InternalEdge e1 : edges) {
			V v1 = e1.target;
			// en v1 tengo cada vecino, y quiero ver con cada otro vecino si es adyacente o no
			for (InternalEdge e2 : edges) {
				V v2 = e2.target;
				// ahora ya tengo cada par de vertices
				for (InternalEdge e : adjList.get(v1)) {
					V v3 = e.target;
					if (v2.equals(v3)) {
						triangles++;
					}
				}
			}
		}

		triangles /= 2;

        return triangles / (double) potentialTriangles;
    }
	
	
	
	@Override
	public void addEdge(V aVertex, V otherVertex, E theEdge) {

		// validacion y creacion de vertices si fuera necesario
		super.addEdge(aVertex, otherVertex, theEdge);

		
		Collection<InternalEdge> adjListSrc = getAdjacencyList().get(aVertex);

		// if exists edge with same target...
		for (InternalEdge internalEdge : adjListSrc) {
			if (internalEdge.target.equals(otherVertex))
				throw new IllegalArgumentException( "Simple Graph: cannot have repeated edges" );
		}
		

		// creacion de ejes
		adjListSrc.add(new InternalEdge(theEdge, otherVertex));

		if (!isDirected ) {
			Collection<AdjacencyListGraph<V, E>.InternalEdge> adjListDst = getAdjacencyList().get(otherVertex);
			adjListDst.add(new InternalEdge(theEdge, aVertex));
		}
	
	}



}