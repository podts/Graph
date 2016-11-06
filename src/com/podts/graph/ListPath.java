package com.podts.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListPath<V extends Vertex,E extends Edge> implements Path<V,E> {
	
	private final LinkedList<E> rEdges;
	public final List<E> edges;
	
	private V last;
	
	public List<E> getEdges() {
		return edges;
	}
	
	public E getLastEdge() {
		return rEdges.getLast();
	}
	
	@Override
	public V getLastVertex() {
		return last;
	}
	
	@Override
	public ListPath<V,E> extend(E e) {
		rEdges.addLast(e);
		return this;
	}
	
	public ListPath() {
		rEdges = new LinkedList<E>();
		edges = Collections.unmodifiableList(rEdges);
	}
	
	public ListPath(List<E> path) {
		rEdges = new LinkedList<E>(path);
		edges = Collections.unmodifiableList(rEdges);
	}
	
	public ListPath(ListPath<V,E> oldPath) {
		rEdges = new LinkedList<E>(oldPath.rEdges);
		edges = Collections.unmodifiableList(rEdges);
	}

	@Override
	public boolean contains(Edge e) {
		return rEdges.contains(e);
	}

	@Override
	public int getLength() {
		return edges.size();
	}
	
}
