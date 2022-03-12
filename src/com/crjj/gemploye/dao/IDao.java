package com.crjj.gemploye.dao;

import java.util.List;

public interface IDao<T> {

	List<T> findAll();

	T findById(int id);

	T save(T obj);

	void update(T obj);

	void delete(int id);
	List<Object[]> employerParDep();

}
