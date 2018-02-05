package fr.acceis.services.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Matiere")
public class Matiere
{
	@Id
	private long	id;
	private String	nom;
	//TODO
	@Transient
	private Collection<Cursus> cursus;
	//TODO
	@Transient
	private Collection<Cours> cours;

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

	public Collection<Cours> getCours()
	{
		return this.cours;
	}

	public void setCours(Collection<Cours> cours)
	{
		this.cours = cours;
	}

	public Collection<Cursus> getCursus()
	{
		return this.cursus;
	}

	public void setCursus(Collection<Cursus> cursus)
	{
		this.cursus = cursus;
	}

}
