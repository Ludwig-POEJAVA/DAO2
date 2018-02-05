package fr.acceis.services.model;

import java.util.Collection;

public class Salle {

	private long id;
	
	private String nom;
	
	private Collection<Creneau> creneaux;

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

	public Collection<Creneau> getCreneaux() {
		return creneaux;
	}

	public void setCreneaux(Collection<Creneau> creneaux) {
		this.creneaux = creneaux;
	}
}
