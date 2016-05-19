package com.podts.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SimpleVertex<E extends Edge> implements Vertex {
	
	public final Graph g;
	protected final Set<E> realEdges = new HashSet<E>();
	public final Set<E> edges;
	
	@Override
	public Set<E> getEdges() {
		return edges;
	}

	@Override
	public Graph getGraph() {
		return g;
	}
	
	public SimpleVertex(Graph graph) {
		g = graph;
		edges = Collections.unmodifiableSet(realEdges);
	}
	
}
