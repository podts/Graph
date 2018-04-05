package com.podts.podgraph;

import java.util.Collection;
import java.util.HashSet;

public class HashGraph implements Graph {
	
	private final Collection<Vertex> vertexs = new HashSet<>();
	
	
	@Override
	public Collection<? extends Vertex> getVertexs() {
		return vertexs;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Vertex createVertex() {
		Vertex v = new SimpleVertex(this);
		vertexs.add(v);
		return v;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Edge createEdge(Vertex v, Vertex x) {
		SimpleVertex sv = (SimpleVertex) v;
		SimpleVertex sx = (SimpleVertex) x;
		Edge e = new SimpleEdge(sv,sx);
		sv.realEdges.add(e);
		sx.realEdges.add(e);
		return e;
	}

	@Override
	public Class<? extends Path<?, ?>> getPathClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends Vertex> getVertexClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends Edge> getEdgeClass() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
