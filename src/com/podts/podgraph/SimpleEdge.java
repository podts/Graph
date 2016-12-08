package com.podts.podgraph;

public class SimpleEdge<V extends Vertex> implements Edge {
	
	public final Pair<V,V> pair;
	
	@Override
	public Pair<V,V> getVertexs() {
		return pair;
	}
	
	public SimpleEdge(V a, V b) {
		pair = new Pair<V,V>(a,b);
	}
	
}
