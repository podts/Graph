package com.podts.podgraph;

import java.util.List;
import java.util.stream.Stream;

public interface Path<V extends Vertex,E extends Edge> {
	
	public List<E> getEdges();
	
	public default Stream<E> edges() {
		return getEdges().stream();
	}
	
	public E getLastEdge();
	public V getLastVertex();
	
	public int getLength();
	
	public Path<V,E> extend(E e);
	public boolean contains(Edge e);
	
}
