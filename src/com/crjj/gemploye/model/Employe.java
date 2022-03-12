package com.crjj.gemploye.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employe {

	@Id
	@GeneratedValue

	private int code;
	private String nom;
	private String prenom;
	private String departement;

	private double salaire;

	public Employe() {

	}

	public Employe(String nom, String prenom, String departement, double salaire) {
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.salaire = salaire;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

}
