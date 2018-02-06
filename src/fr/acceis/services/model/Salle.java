package fr.acceis.services.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Salle")
public class Salle
{
	@Id
	private long	id;
	private String	nom;

	@OneToMany
	private Collection<Creneau> creneaux;

	public long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getNom()
	{
		return this.nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public Collection<Creneau> getCreneaux()
	{
		return this.creneaux;
	}

	public void setCreneaux(Collection<Creneau> creneaux)
	{
		this.creneaux = creneaux;
	}
}
