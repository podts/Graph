package com.podts.podgraph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
	
	public static final Stream<Collection<Vertex>> disjoints(Graph g) {
		return StreamSupport.stream(new DisjointIterator(g).spliterator(), false);
	}
	
	public static final boolean isConnected(Graph g) {
		return !disjoints(g).skip(1).findFirst().isPresent();
	}
	
	public static final boolean isDisjoint(Graph g) {
		return !isConnected(g);
	}
	
	private static class DisjointIterator implements Iterable<Collection<Vertex>>, Iterator<Collection<Vertex>> {
		
		private Collection<Vertex> verts = new HashSet<>();
		
		@Override
		public Iterator<Collection<Vertex>> iterator() {
			return this;
		}
		
		@Override
		public boolean hasNext() {
			return !verts.isEmpty();
		}
		
		@Override
		public Collection<Vertex> next() {
			Collection<Vertex> island = new HashSet<>();
			Collection<Edge> edges = new HashSet<>();
			Collection<Edge> newEdges = new HashSet<>();
			
			Vertex v = pop();
			island.add(v);
			edges.addAll(v.getEdges());
			while(!edges.isEmpty()) {
				for(Edge e : edges) {
					Vertex o = e.getOther(v);
					if(!island.contains(o)) {
						island.add(o);
						verts.remove(o);
						newEdges.addAll(o.getEdges());
					}
				}
				edges.clear();
				edges.addAll(newEdges);
				newEdges.clear();
			}
			
			return island;
		}
		
		private Vertex pop() {
			Iterator<Vertex> it = verts.iterator();
			Vertex v = it.next();
			it.remove();
			return v;
		}
		
		DisjointIterator(Graph g) {
			verts.addAll(g.getVertexs());
		}
		
	}
	
}
