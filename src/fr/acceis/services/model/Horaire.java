package fr.acceis.services.model;

import java.util.Collection;
import java.util.Date;

public class Horaire {

	private long id;

	private Date debut;
	
	private Date fin;
	
	private Collection<Creneau> creneaux;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public Collection<Creneau> getCreneaux() {
		return creneaux;
	}

	public void setCreneaux(Collection<Creneau> creneaux) {
		this.creneaux = creneaux;
	}

}
