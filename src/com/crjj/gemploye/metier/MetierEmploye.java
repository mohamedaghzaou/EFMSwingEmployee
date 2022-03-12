package com.crjj.gemploye.metier;

import java.util.List;

import com.crjj.gemploye.dao.DaoEmploye;
import com.crjj.gemploye.dao.IDao;
import com.crjj.gemploye.model.Employe;

public class MetierEmploye implements IMetier<Employe> {

	private IDao<Employe> doaEmploye = new DaoEmploye();

	@Override
	public List<Employe> findAll() {
		return doaEmploye.findAll();
	}

	@Override
	public Employe findById(int id) {
		return doaEmploye.findById(id);
	}

	@Override
	public Employe save(Employe obj) {
		return doaEmploye.save(obj);
	}

	@Override
	public void update(Employe obj) {
		doaEmploye.update(obj);

	}

	@Override
	public void delete(int id) {
		doaEmploye.delete(id);

	}

	@Override
	public List<Object[]> employerParDep() {
		
		return doaEmploye.employerParDep();
	}

}
