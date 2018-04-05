package com.podts.podgraph;

import java.util.Set;
import java.util.stream.Stream;

public interface Vertex {
	
	public Set<? extends Edge> getEdges();
	
	public default Stream<? extends Edge> edges() {
		return getEdges().stream();
	}
	
	public Graph getGraph();
	
	public default boolean isIn(Graph g) {
		return getGraph().equals(g);
	}
	
	public default boolean isConnectedTo(Vertex v) {
		if(v == null || !v.isIn(getGraph())) return false;
		for(Edge edge : getEdges()) {
			if(edge.contains(v)) return true;
		}
		return false;
	}
	
}
