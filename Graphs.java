package com.podts.graph;

import java.util.Collection;

public class Graphs {
	
	public static Graph unModifiableGraph(Graph g) {
		return new SafeGraph(g);
	}
	
	private static class SafeGraph implements Graph {
		
		private final Graph g;
		
		@Override
		public Collection<? extends Vertex> getVertexs() {
			return g.getVertexs();
		}

		@Override
		public Vertex createVertex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Edge createEdge(Vertex v, Vertex x) {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public Class<?> getVertexClass() {
			return g.getVertexClass();
		}

		@Override
		public Class<?> getEdgeClass() {
			return g.getEdgeClass();
		}
		
		private SafeGraph(Graph g) {
			this.g = g;
		}

		@Override
		public Class<? extends Path<?, ?>> getPathClass() {
			return g.getPathClass();
		}
		
	}
	
}
