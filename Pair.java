package com.podts.graph;

public class Pair<U,T> {
	
	public final U first;
	public final T second;
	
	public final boolean contains(Object o) {
		return first.equals(o) || second.equals(o);
	}
	
	@Override
	public String toString() {
		return "(" + first + ", " + second + ")";
	}
	
	public Pair(U f, T s) {
		first = f;
		second = s;
	}
	
}
