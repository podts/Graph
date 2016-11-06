package com.podts.graph;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Function;

public interface Graph {
	
	public static final Comparator<Path<?,?>> shortestPathByEdges = new Comparator<Path<?,?>>() {
		@Override
		public int compare(Path<?,?> a, Path<?,?> b) {
			return a.getLength() - b.getLength();
		}
	};
	
	public Collection<? extends Vertex> getVertexs();

	public Vertex createVertex();

	public Edge createEdge(Vertex v, Vertex x);
	
	public Class<? extends Path<?,?>> getPathClass();
	public Class<? extends Vertex> getVertexClass();
	public Class<? extends Edge> getEdgeClass();
	
	@SuppressWarnings("unchecked")
	public default <P extends Path<V,E>, V extends Vertex, E extends Edge> P getPath(V start, Comparator<P> comp, Function<P,Boolean> criteria, Function<P,Boolean> acceptor) {
		
		Graph g = start.getGraph();
		
		P nowhere;
		try {
			nowhere = (P) g.getPathClass().newInstance();
			if(criteria.apply(nowhere))
				return nowhere;
		} catch (Exception e) {
			
		}
		
		Queue<P> paths = new PriorityQueue<P>(1, comp);
		
		for(Edge e : start.getEdges()) {
			P p;
			try {
				p = (P) g.getPathClass().getConstructor(g.getVertexClass(),g.getEdgeClass()).newInstance((V)start, (E)e);
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
			if(!acceptor.apply(p)) continue;
			if(criteria.apply(p)) return p;
			paths.add(p);
		}

		while(!paths.isEmpty()) {
			P p = paths.poll();
			V latest = p.getLastVertex();
			for(Edge e : latest.getEdges()) {
				if(p.contains(e)) continue;
				P np = (P) p.extend((E)e);
				if(!acceptor.apply(np)) continue;
				if(criteria.apply(np)) return np;
				paths.add(np);
			}
		}

		return null;
	}
	
	public default <P extends Path<V,E>, V extends Vertex, E extends Edge> P getPath(V start, Comparator<P> comp, Function<P,Boolean> criteria) {
		
		return getPath(start, comp, criteria, new Function<P,Boolean>() {
			@Override
			public Boolean apply(P p) {
				return true;
			}
		});
	}
	
	public default <P extends Path<V,E>, V extends Vertex, E extends Edge> P getPath(V start, Function<P,Boolean> criteria) {
		
		return getPath(start, new Comparator<P>() {
			@Override
			public int compare(P a, P b) {
				return a.getLength() - b.getLength();
			}
		}, criteria, new Function<P,Boolean>() {
			@Override
			public Boolean apply(P p) {
				return true;
			}
		});
		
	}
	
	public default <P extends Path<V,E>, V extends Vertex, E extends Edge> P getPath(V start, V finish, Comparator<P> comp, Function<P,Boolean> acceptor) {
		
		return getPath(start, new Comparator<P>() {
			@Override
			public int compare(P a, P b) {
				return a.getLength() - b.getLength();
			}
		}, new Function<P,Boolean>() {
			@Override
			public Boolean apply(P path) {
				if(path.getLastVertex() != null && path.getLastVertex().equals(finish)) return true;
				return false;
			}
		}, acceptor);
	}
	
	public default <P extends Path<V,E>, V extends Vertex, E extends Edge> P getPath(V start, V finish, Comparator<P> comp) {
		
		return getPath(start, comp, new Function<P,Boolean>() {
			@Override
			public Boolean apply(P path) {
				if(path.getLastVertex() != null && path.getLastVertex().equals(finish)) return true;
				return false;
			}
		}, new Function<P,Boolean>() {
			@Override
			public Boolean apply(P path) {
				return true;
			}
		});
		
	}
	
	public default <P extends Path<V,E>, V extends Vertex, E extends Edge> P getPath(V start, V finish) {
		
		return getPath(start, new Comparator<P>() {
			@Override
			public int compare(P a, P b) {
				return a.getLength() - b.getLength();
			}
		}, new Function<P,Boolean>() {
			@Override
			public Boolean apply(P path) {
				if(path.getLastVertex().equals(finish)) return true;
				return false;
			}
		}, new Function<P,Boolean>() {
			@Override
			public Boolean apply(P path) {
				return true;
			}
		});
		
	}
	
	
	@SuppressWarnings("unchecked")
	public default <V extends Vertex, E extends Edge> List<V> getVertexPath(V start, V finish) {

		LinkedList<LinkedList<V>> paths = new LinkedList<LinkedList<V>>();

		LinkedList<V> firstPath = new LinkedList<V>();
		firstPath.add(start);

		paths.addFirst(firstPath);

		while(!paths.isEmpty()) {
			LinkedList<V> p = paths.pop();
			Vertex last = p.getLast();
			for(Edge e : last.getEdges()) {
				V other = (V) e.getOther(last);
				if(p.contains(other)) continue;
				LinkedList<V> np = new LinkedList<V>(p);
				np.add(other);
				if(other.equals(finish)) return np;
				paths.add(np);
			}
		}

		return null;

	}

}
