package com.podts.podgraph;

import java.util.Collection;

public class Graphs {
	
	public static final Graph unModifiableGraph(Graph g) {
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
		public Class<? extends Vertex> getVertexClass() {
			return g.getVertexClass();
		}

		@Override
		public Class<? extends Edge> getEdgeClass() {
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
