package fr.acceis.services.model;

import java.util.Collection;

public class Cursus {

	private String nom;
	
	private Collection<Matiere> matieres;
	
	private Collection<Etudiant> etudiants;
	
	private long id;

	public Collection<Matiere> getMatieres() {
		return matieres;
	}

	public void setMatieres(Collection<Matiere> matieres) {
		this.matieres = matieres;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Collection<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(Collection<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}
	
}
