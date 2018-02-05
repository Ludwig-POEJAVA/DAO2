package fr.acceis.services.model;

import java.util.Collection;

public class Matiere {
	
	private long id;
	
	private String nom;
	
	private Collection<Cursus> cursus;
	
	private Collection<Cours> cours;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Collection<Cours> getCours() {
		return cours;
	}

	public void setCours(Collection<Cours> cours) {
		this.cours = cours;
	}

	public Collection<Cursus> getCursus() {
		return cursus;
	}

	public void setCursus(Collection<Cursus> cursus) {
		this.cursus = cursus;
	}
	
}
