package com.podts.podgraph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SimpleVertex<G extends Graph, E extends Edge> implements Vertex {
	
	public final G g;
	protected final Set<E> realEdges = new HashSet<E>();
	public final Set<E> edges;
	
	@Override
	public Set<E> getEdges() {
		return edges;
	}

	@Override
	public G getGraph() {
		return g;
	}
	
	public SimpleVertex(G graph) {
		g = graph;
		edges = Collections.unmodifiableSet(realEdges);
	}
	
}
