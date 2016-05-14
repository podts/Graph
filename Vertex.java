package com.podts.graph;

import java.util.Set;

public interface Vertex {
	
	public Set<? extends Edge> getEdges();
	
	public Graph getGraph();
	
	public default boolean isIn(Graph g) {
		return getGraph() == g;
	}
	
	public default boolean isConnectedTo(Vertex v) {
		for(Edge edge : getEdges()) {
			if(edge.contains(v)) return true;
		}
		return false;
	}
	
}
