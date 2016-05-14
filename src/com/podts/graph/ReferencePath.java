package com.podts.graph;

import java.util.LinkedList;
import java.util.List;

public class ReferencePath<V extends Vertex,E extends Edge> implements Path<V,E> {
	
	protected final ReferencePath<V,E> previous;
	public final E edge;
	public final V last;
	
	@Override
	public List<E> getEdges() {
		return populateList(new LinkedList<E>());
	}
	
	public final Path<V,E> complete() {
		return new ListPath<V,E>(createList());
	}
	
	private List<E> createList() {
		return populateList(new LinkedList<E>());
	}
	
	private List<E> populateList(List<E> list) {
		if(previous == null) {
			list.add(edge);
			return list;
		}
		previous.populateList(list);
		list.add(edge);
		return list;
	}
	
	@Override
	public E getLastEdge() {
		if(previous == null)
			return edge;
		return previous.getLastEdge();
	}
	
	@Override
	public V getLastVertex() {
		return last;
	}
	
	public ReferencePath<V,E> extend(E e) {
		return new ReferencePath<V,E>(this, e);
	}
	
	@Override
	public boolean contains(Edge e) {
		if(edge.equals(e)) return true;
		else if (previous != null) return previous.contains(e);
		return false;
	}
	
	public ReferencePath() {
		previous = null;
		last = null;
		edge = null;
	}
	
	@SuppressWarnings("unchecked")
	public ReferencePath(V v,E e) {
		previous = null;
		last = (V) e.getOther(v);
		edge = e;
	}
	
	@SuppressWarnings("unchecked")
	public ReferencePath(ReferencePath<V,E> p, E e) {
		previous = p;
		edge = e;
		last = (V) e.getOther(p.getLastVertex());
	}
	
}
