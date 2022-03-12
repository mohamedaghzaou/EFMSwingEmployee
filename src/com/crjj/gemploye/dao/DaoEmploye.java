package com.crjj.gemploye.dao;

import java.util.List;

import org.hibernate.Session;

import com.crjj.gemploye.hibernateUtils.HibernateUtils;
import com.crjj.gemploye.model.Employe;

public class DaoEmploye implements IDao<Employe> {

	@Override
	public List<Employe> findAll() {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		s.beginTransaction();

		List<Employe> employes = s.createQuery("from Employe", Employe.class).getResultList();
		s.getTransaction().commit();
		s.close();
		return employes;
	}

	@Override
	public Employe findById(int id) {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		s.beginTransaction();

		Employe employe = s.get(Employe.class, id);
		s.getTransaction().commit();
		s.close();
		return employe;
	}

	@Override
	public Employe save(Employe obj) {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(obj);
		s.getTransaction().commit();
		s.close();
		return obj;
	}

	@Override
	public void update(Employe obj) {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.update(obj);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public void delete(int id) {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Employe obj = new Employe();
		obj.setCode(id);
		s.beginTransaction();
		s.delete(obj);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public List<Object[]> employerParDep() {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		s.beginTransaction();

		List<Object[]>  emp = s
				.createQuery("select departement , count(*) from Employe group by departement")
				.getResultList();
		s.getTransaction().commit();
		s.close();
		return emp;
	}

}
