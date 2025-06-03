package ExamenesViejos.P2_nica.Ej2;


import ExamenesViejos.P2_nica.Ej2.GraphService.*;
//import dario.SimpleOrDefault;	// ATENCIÓN: CAMBIAR AQUI CON SU NOMBRE DE USUARIO.

abstract public class GraphFactory<V, E> {

	public static <V, E> GraphService<V, E> create(Multiplicity edgeMultiplicity, EdgeMode theEdgeMode, 
			SelfLoop acceptSelfLoops, Weight hasWeight, Storage theStorage) {
		
		if (theStorage== Storage.SPARSE)  // manejando 8 tipos con 2 clases concretas
			if (edgeMultiplicity== Multiplicity.SIMPLE)
				return new SimpleOrDefault<V,E>(theEdgeMode==EdgeMode.DIRECTED, 
												acceptSelfLoops==SelfLoop.YES, 
											    hasWeight==Weight.YES );
			else
				return new Multi<V,E>(theEdgeMode==EdgeMode.DIRECTED, 
											acceptSelfLoops==SelfLoop.YES, 
											hasWeight==Weight.YES );
		
		// todavia no lo hemos implementado en forma Densa Matriz
		//	return new AdjacencymatrixGraph<V,E>(isSimple, isDirected, isWeighted );
		
		
		throw new RuntimeException("Not yet implemented");
	}
	
	private GraphFactory() {
	}
}