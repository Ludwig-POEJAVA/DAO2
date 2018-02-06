package fr.acceis.services.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Creneau")
public class Creneau
{
	@Id
	private long id;

	@ManyToOne
	private Salle salle;

	@ManyToOne
	private Horaire horaire;

	@OneToOne
	private Cours cours;

	public long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Salle getSalle()
	{
		return this.salle;
	}

	public void setSalle(Salle salle)
	{
		this.salle = salle;
	}

	public Horaire getHoraire()
	{
		return this.horaire;
	}

	public void setHoraire(Horaire horaire)
	{
		this.horaire = horaire;
	}

	public Cours getCours()
	{
		return this.cours;
	}

	public void setCours(Cours cours)
	{
		this.cours = cours;
	}

	@Override
	public String toString()
	{
		String result = "En " + this.getSalle() + "\n";
		result += this.getHoraire();
		return result;
	}

}
