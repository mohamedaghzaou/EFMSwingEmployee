package com.crjj.gemploye.ihm.AbstractTableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.crjj.gemploye.model.Employe;

public class EmployeTableModel extends AbstractTableModel {

	private List<Employe> employes = null;
	private String[] headers = { "Code", "Nom", "Prenom", "Departement", "Salaire" };

	public EmployeTableModel(List<Employe> list) {
		this.employes = list;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return employes.size();
	}

	@Override
	public String getColumnName(int column) {

		return headers[column];
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Employe e = employes.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return e.getCode();
		case 1:
			return e.getNom();
		case 2:
			return e.getPrenom();
		case 3:
			return e.getDepartement();
		case 4:
			return e.getSalaire();

		}
		return null;
	}

	public void remove(int i) {
		employes.remove(i);
		fireTableRowsDeleted(i - 1, i - 1);
	}

	public void add(Employe i) {
		employes.add(i);
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	}

	public Employe getEmployeByIndex(int i) {
		return employes.get(i);
	}

}
