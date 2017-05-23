package com.tharun.docker.springboot;

public interface Callback<T> {
	public void notify(T t);
	//void persist(V v);
}
