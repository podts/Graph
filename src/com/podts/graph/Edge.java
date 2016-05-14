package com.podts.graph;

public interface Edge {
	
	public Pair<? extends Vertex,? extends Vertex> getVertexs();
	
	public default boolean contains(Vertex v) {
		return getVertexs().contains(v);
	}
	
	public default Vertex getOther(Vertex v) {
		Pair<? extends Vertex,? extends Vertex> p = getVertexs();
		if(p.first.equals(v)) return p.second;
		return p.first;
	}
	
	public default Vertex getSimilar(Edge e) {
		if(e == null) return null;
		Pair<? extends Vertex,? extends Vertex> v = getVertexs();
		Pair<? extends Vertex,? extends Vertex> ov = e.getVertexs();
		if(v.first == ov.first || v.first == ov.second)
			return v.first;
		if(v.second == ov.first || v.second == ov.second)
			return v.second;
		return null;
	}
	
}
