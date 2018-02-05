package fr.acceis.services.model;

public class Creneau {

	private long id;
	
	private Salle salle;
	
	private Horaire horaire;
	
	private Cours cours;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Horaire getHoraire() {
		return horaire;
	}

	public void setHoraire(Horaire horaire) {
		this.horaire = horaire;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}
	
	public String toString() {
		String result = "En " + getSalle() + "\n";
		result += getHoraire();
		return result;
	}
	
}
