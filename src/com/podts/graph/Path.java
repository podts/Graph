package com.podts.graph;

import java.util.List;

public interface Path<V extends Vertex,E extends Edge> {
	
	public List<E> getEdges();
	public E getLastEdge();
	public V getLastVertex();
	
	public int getLength();
	
	public Path<V,E> extend(E e);
	public boolean contains(Edge e);
	
}
