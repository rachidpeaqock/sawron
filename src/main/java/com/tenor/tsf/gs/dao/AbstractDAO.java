package com.tenor.tsf.gs.dao;

import java.util.List;

public interface AbstractDAO<T> {

	public List<T> getAll();

	public T create(T obj) ;

	public void update(T obj) ;

	public void delete(T obj);

}
