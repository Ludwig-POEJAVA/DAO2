package fr.acceis.services.model;

import java.util.Collection;

public class Cours {

	private long id;
	
	private Matiere matiere;
	
	private Creneau creneau;
	
	private Collection<Professeur> professeurs;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Collection<Professeur> getProfesseurs() {
		return professeurs;
	}

	public void setProfesseurs(Collection<Professeur> professeurs) {
		this.professeurs = professeurs;
	}

	public Creneau getCreneau() {
		return creneau;
	}

	public void setCreneau(Creneau creneau) {
		this.creneau = creneau;
	}
	
}
