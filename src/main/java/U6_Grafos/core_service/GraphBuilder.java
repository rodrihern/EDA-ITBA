package U6_Grafos.core_service;

import U6_Grafos.core.GraphFactory;
import U6_Grafos.core_service.GraphService.*;

public class GraphBuilder<V,E> {
	private GraphService.Multiplicity multiplicity= GraphService.Multiplicity.SIMPLE;
	private GraphService.EdgeMode edgeMode= EdgeMode.DIRECTED;
	private SelfLoop acceptSelfLoops= SelfLoop.NO;
	private Weight hasWeight= Weight.NO;
	private Storage implementation= Storage.SPARSE;
	
	public GraphBuilder<V,E> withMultiplicity(Multiplicity param) {
		this.multiplicity= param;
		return this;
	}
	
	public GraphBuilder<V,E> withDirected(EdgeMode param) {
		this.edgeMode= param;
		return this;
	}
	
	public GraphBuilder<V,E> withAcceptSelfLoop(SelfLoop param) {
		this.acceptSelfLoops= param;
		return this;
	}
	
	public GraphBuilder<V,E> withAcceptWeight(Weight param) {
		this.hasWeight= param;
		return this;
	}
	
	public GraphBuilder<V,E> withStorage(Storage param) {
		this.implementation= param;
		return this;
	}
	
	public GraphService<V,E> build() {
	  return GraphFactory.create(multiplicity, edgeMode, acceptSelfLoops, hasWeight, implementation);
  }

}
